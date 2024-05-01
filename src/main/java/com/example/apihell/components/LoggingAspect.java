package com.example.apihell.components;

import com.example.apihell.service.CounterService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final CounterService counterService;
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect(CounterService counterService) {
        this.counterService = counterService;
    }

    @Pointcut("execution(* com.example.apihell.service.*.*(..))")
    public void serviceMethods() { }

    @Pointcut("execution(* com.example.apihell.controller.*.*(..))")
    public void controllerMethods() { }

    @Before("controllerMethods()")
    public void accessCounter(){
        logger.info("Counter = {}", counterService.incrementAndGet());
    }

    @Before("serviceMethods()")
    public void logServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Service method invoked - Class: {}, Method: {}, Args: {}", className, methodName, args);
    }
}
