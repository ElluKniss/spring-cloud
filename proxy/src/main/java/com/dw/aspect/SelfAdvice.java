package com.dw.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SelfAdvice {

//    @Pointcut("@annotation(com.dw.aspect.SelfDefine)")
//    private void method(){
//
//    }

    @Around("@annotation(com.dw.aspect.SelfDefine)")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        System.out.println(1);
        Object proceed = point.proceed();
        System.out.println(2);
        return proceed;
    }

}
