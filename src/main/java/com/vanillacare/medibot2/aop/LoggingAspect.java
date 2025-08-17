package com.vanillacare.medibot2.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // all methods in services
    @Pointcut("within(com.vanillacare.medibot2.service..*)")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("⬅️ Entering {} with args {}", method, Arrays.toString(args));
        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;
            log.info("⬅️ Exiting {} with result {} ({} ms)", method, result, elapsed);
            return result;
        } catch (Exception ex) {
        log.error("❌ Exception in {}: {}", method, ex.getMessage(), ex);
        throw ex;
        }
    }
}
