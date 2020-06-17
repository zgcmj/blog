package com.cmj.blog.aspect;


import com.cmj.blog.doamin.ResultLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect     //切面
@Component //加入
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.cmj.blog.controller.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        Signature signature = joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String name = signature.getName();

        Object[] args = joinPoint.getArgs();
        ResultLog resultLog = new ResultLog();
        resultLog.setUrl(requestURI);
        resultLog.setIp(remoteAddr);
        resultLog.setArgs(args);
        resultLog.setClassMethod(name+declaringTypeName);
        logger.info("Request: {}",resultLog);
    }

    @After("log()")
    public void after(){
       logger.info("++++++++++doafter");
    }


    @AfterReturning(returning ="result" ,pointcut = "log()")
    public void afterreturn(Object result){
        logger.info("Result: {}", result);
    }



}
