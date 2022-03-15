package edu.miu.cs544.sujan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectJLogging {

    @AfterReturning(pointcut = "execution(* edu.miu.cs544.sujan.service.impl.JobServiceImpl.delete*(..))", returning = "success")
    public void after(JoinPoint joinPoint, String success) {
        System.out.println(success);
    }

    @AfterThrowing(pointcut = "execution(* edu.miu.cs544.sujan.service.impl.JobServiceImpl.delete*(..))", throwing = "exception")
    public void after(JoinPoint joinPoint, Exception exception) {
        System.out.println(exception.getMessage());
    }
}
