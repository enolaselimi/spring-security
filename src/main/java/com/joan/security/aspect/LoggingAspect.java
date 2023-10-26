package com.joan.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.joan.security.service..*(..)) || execution(* com.joan.security.controller..*(..))")
    public Object logServices(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logger.info("Executing {} inside {}",proceedingJoinPoint.getSignature().getName() , proceedingJoinPoint.getThis().getClass().getSimpleName());
        return proceedingJoinPoint.proceed();
    }
}
