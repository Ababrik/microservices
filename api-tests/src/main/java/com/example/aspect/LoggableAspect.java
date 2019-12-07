package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Aspect
@Slf4j
public class LoggableAspect {

    @Pointcut("execution(* com.example.services.ApiService+.*(..))")
    public void anyMethodInService() {

    }

    @Before("anyMethodInService()")
    public void step(JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String name = joinPoint.getArgs().length > 0
                ? String.format("%s (%s)", methodSignature.getName(), arrayToString(joinPoint.getArgs()))
                : methodSignature.getName() + "()";
        log.info(name);

    }

    private static String arrayToString(final Object... array) {
        return Stream.of(array)
                .map(object -> {
                    if (object.getClass().isArray()) {
                        return Objects.toString((Object[]) object);
                    }
                    return Objects.toString(object);
                })
                  .collect(Collectors.joining(", "));

    }

}
