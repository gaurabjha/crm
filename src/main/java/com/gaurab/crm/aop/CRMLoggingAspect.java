package com.gaurab.crm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CRMLoggingAspect {

	// setup logger
	private static final Logger logger = LoggerFactory.getLogger(CRMLoggingAspect.class);

	// setup pointcut declaration
	@Pointcut("execution(* com.gaurab.crm.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.gaurab.crm.service.*.*(..))")
	private void forService() {
	}

	@Pointcut("execution(* com.gaurab.crm.dao.*.*(..))")
	private void forDAO() {
	}

	@Pointcut("forControllerPackage() || forService() || forDAO() ")
	public void forAppFlow() {
	}

	// add @Before Advice
	@Before("forAppFlow()")
	public void forAppFlowAdvice(JoinPoint theJointPoint) {
		// display method we are calling
		String theMethod = theJointPoint.getSignature().toShortString();
		logger.info("=====> in @Before : calling Method : {}", theMethod);

		// display the arguements being passed to the method
		// get the arguements

		Object[] args = theJointPoint.getArgs();

		// loop through the args
		for (Object tempArg : args) {
			logger.info("====>> arguements : {}", tempArg);
		}
	}

	
	// add @AfterReturning Advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
	public void afterRetrningAdvice(JoinPoint theJoinPoint, Object theResult) {
		
		// display rthe method returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		logger.info("=====>> in @AfterReturning : calling Method : {}", theMethod);

		// display data returned
		logger.info("====>> Returned Value : {}", theResult);
		
	}

}
