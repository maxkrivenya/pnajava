package com.example.apihell.components;

import com.example.apihell.service.CounterService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterAspect {

    private final CounterService counterService;
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public CounterAspect(CounterService counterService) {
        this.counterService = counterService;
    }

    @Pointcut("execution(* com.example.apihell.controller.*.*(..))")
    public void controllerMethods() { }

    @Before("controllerMethods()")
    public synchronized void accessCounter(){
        logger.info("Counter = {}", counterService.incrementAndGet());
    }

}
