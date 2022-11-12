package com.ruoyi.web.core.config;

import com.plumelog.trace.aspect.AbstractAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig extends AbstractAspect {

    @Around("within(com.ruoyi.*)")//这边写自己的包的路径
    public Object around(JoinPoint joinPoint) throws Throwable {
        return aroundExecute(joinPoint);
    }
}