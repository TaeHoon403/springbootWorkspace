package com.kh.app0121;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class KhAop {

    @Before("execution( public * com..MemberService.*(..))")
    public void m01(){
        System.out.println("m01 called~~~");
    }

    @Around("execution( public * com..MemberService.*(..))")
    public Object m02(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("m02 start~~~");
        Object result = jp.proceed();
        System.out.println("m02 end~~~");
        return result;
    }

}
