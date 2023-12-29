package com.example.internetshop.service.logging;

import com.example.internetshop.config.ApplicationContextProvider;
import com.example.internetshop.service.auth.UsernamePasswordAuthenticationService;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.example.internetshop.controller.AuthController.TOKEN_HEADER_NAME;

@AllArgsConstructor
public class LoggingOnTheFlyInvocationHandler implements InvocationHandler {

    private final Set<String> methods;
    private final Object bean;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        // TODO: finally block to clean up
        if (methods.contains(method.getName())) {
            getToken()
                    .map(token -> getUserService().authenticate(token))
                    .ifPresent(user -> MDC.put("login.name", user.getName()));

            MDC.put("method.name", method.getName());
        }
        // 07/19/2022 16:47:23 - Igor, createOrder
        Object invoke = method.invoke(bean, args);

        // Clean up
        MDC.remove("method.name");
        MDC.remove("login.name");

        return invoke;
    }

    private UsernamePasswordAuthenticationService getUserService() {
        return ApplicationContextProvider.getBean(UsernamePasswordAuthenticationService.class);
    }

    private Optional<String> getToken() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .flatMap(request -> Arrays.stream(request.getCookies())
                        .filter(cookie -> Objects.equals(cookie.getName(), TOKEN_HEADER_NAME))
                        .map(Cookie::getValue)
                        .findFirst());
    }
}
