package com.hades.foxtube.controller.aspect;

import java.lang.reflect.Parameter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

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
    StringBuilder logMessage =
        new StringBuilder("\nMethod: ")
            .append(joinPoint.getSignature().getName())
            .append(" is called\n");

    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Parameter[] parameters = methodSignature.getMethod().getParameters();
    Object[] args = joinPoint.getArgs();

    for (int i = 0; i < parameters.length; i++) {
      logMessage
          .append("Parameter name: ")
          .append(parameters[i].getName())
          .append(", Value: ")
          .append(args[i])
          .append("\n");
    }

    logger.info(logMessage.toString());
  }

  @AfterReturning(pointcut = "controller()", returning = "result")
  public void afterController(JoinPoint joinPoint, Object result) {
    logger.info(
        "\nMethod: {} is finished.\nResult: {}", joinPoint.getSignature().getName(), result);
  }

  @AfterThrowing(pointcut = "controller()", throwing = "exception")
  public void afterThrowingController(JoinPoint joinPoint, Throwable exception) {
    logger.error(
        "\nMethod: {} throws exception: {}",
        joinPoint.getSignature().getName(),
        exception.getMessage());
  }
}
