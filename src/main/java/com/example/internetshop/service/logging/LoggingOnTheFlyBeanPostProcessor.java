package com.example.internetshop.service.logging;

import com.example.internetshop.service.auth.UsernamePasswordAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoggingOnTheFlyBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Set<Method>> beans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Set<Method> loggingMethods = Arrays.stream(bean.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(LogOnTheFly.class))
                .collect(Collectors.toSet());

        if (!loggingMethods.isEmpty()) {
            beans.put(beanName, loggingMethods);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!beans.containsKey(beanName)) {
            return bean;
        }

        Set<String> methodNames = beans.get(beanName)
                .stream()
                .map(Method::getName)
                .collect(Collectors.toSet());

        Class<?> beanClass = beans.get(beanName)
                .stream()
                .findAny()
                .map(Method::getDeclaringClass)
                .orElseThrow(() -> new RuntimeException("Oops!"));
        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                new LoggingOnTheFlyInvocationHandler(methodNames, bean));
    }
}
