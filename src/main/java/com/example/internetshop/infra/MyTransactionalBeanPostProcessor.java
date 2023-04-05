package com.example.internetshop.infra;

import com.example.internetshop.model.InjectRandomInt;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

@Component
@AllArgsConstructor
public class MyTransactionalBeanPostProcessor implements BeanPostProcessor {

    private final EntityManager entityManager;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectRandomInt.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, bean, field.getAnnotation(InjectRandomInt.class).value());
                });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Object result = method.invoke(proxy, args);
            transaction.commit();
            return result;
        };

        if (bean.getClass().isAnnotationPresent(Transactional.class)) {
            Object wrapper = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(), invocationHandler);
            return wrapper;
        }

        return bean;
    }
}
