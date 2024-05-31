package com.hades.foxtube.controller.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

/**
 * @Author: Hades @Date: 2024/5/31 @Description:
 */
@Aspect
@Component
public class ControllerAspect {
  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ControllerAspect.class);

  @Pointcut("execution(public * com.hades.foxtube.controller.*.*(..))")
  private void controller() {}

  @Before("controller()")
  public void beforeController(JoinPoint joinPoint) {
    logger.info("Method: {} is called", joinPoint.getSignature().getName());

    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Parameter[] parameters = methodSignature.getMethod().getParameters();
    Object[] args = joinPoint.getArgs();

    for (int i = 0; i < parameters.length; i++) {
      logger.info("Parameter name: {}, Value: {}", parameters[i].getName(), args[i]);
    }
  }

  @AfterReturning(pointcut = "controller()", returning = "result")
  public void afterController(JoinPoint joinPoint, Object result) {
    logger.info("Method: {} is finished", joinPoint.getSignature().getName());

    logger.info("Result: {}", result);
  }

  @AfterThrowing(pointcut = "controller()", throwing = "exception")
  public void afterThrowingController(JoinPoint joinPoint, Throwable exception) {
    logger.error(
        "Method: {} throws exception: {}",
        joinPoint.getSignature().getName(),
        exception.getMessage());
  }
}
