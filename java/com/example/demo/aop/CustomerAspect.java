package com.example.demo.aop;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class CustomerAspect {
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	  @Before("execution(* com.example.demo.dao.*.*(..) )") public void
	  beforeAspect(JoinPoint joinPoint) {
	  logger.info("Before aspect : "+joinPoint.getSignature().getName());
	  
	  }
	  
	  @After("execution(* com.example.demo.dao.*.*(..) )") public void
	  afterAspect(JoinPoint joinPoint) {
	  logger.info("After aspect : "+joinPoint.getSignature().getName());
	  
	  }
	 
	
	
	  @AfterReturning(pointcut="execution(* com.example.demo.dao.*.*(..) )",returning="result")
	  public void afterReturnAspect(JoinPoint joinPoint,Object result) {
	  logger.info("After returning aspect : "+joinPoint.getSignature().getName());
	  logger.info("After returning result : "+result);
	  
	  }
	  
	  @AfterThrowing(pointcut="execution(* com.example.demo.dao.*.*(..) )",throwing="error")
	  public void afterThrowingAspect(JoinPoint joinPoint,Throwable error) {
	  logger.info("After returning aspect : "+joinPoint.getSignature().getName());
	  logger.info("After returning result : "+error);
	  
	  }
	 
	
	
	
	/*
	 * @Around("execution(* com.example.demo.dao.*.*(..) )") public void
	 * aroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
	 * logger.info("Around aspect : "+joinPoint.getSignature().getName()); Object
	 * result =joinPoint.proceed();
	 * logger.info("Around aspect : "+joinPoint.getSignature().getName());
	 * 
	 * }
	 */
	 
	 

}
