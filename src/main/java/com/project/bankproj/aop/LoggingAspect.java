package com.project.bankproj.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution( * com.project.bankproj.controller.*.*(..))")
    public void logControllers() {
    }

    @Pointcut("execution( * com.project.bankproj.controller.*.*(..))")
    public void logServices() {
    }

    @Before("logControllers()")
    public void logBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        log.info("Controller request {}:\n" +
                        "IP : {}\n" +
                        "URL : {}\n" +
                        "HTTP_METHOD : {}\n" +
                        "CONTROLLER_METHOD : {}.{}",
                joinPoint.getSignature().getClass(),
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Before("logServices()")
    public void LogBeforeServices(JoinPoint joinPoint) {
        log.info("Run service:\n" +
                        "SERVICE_METHOD : {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "logControllers()")
    public void doAfterReturning(Object returnObject) {
        log.info("\nReturn value: {}\n" +
                        "END OF REQUEST",
                returnObject);
    }

    @AfterThrowing(throwing = "e", pointcut = "logControllers()")
    public void throwException(JoinPoint joinPoint, Exception e) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(joinPoint.getArgs()), e.getMessage());
    }
}