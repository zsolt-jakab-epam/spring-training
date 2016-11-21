package com.jazs.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
	
	@Pointcut("bean(*a*)")
	public void withinDemo() {
	}
	
	@Before("withinDemo()")
	public void withinDemoAdvice() {
		System.out.println("********** WITHIN advice **********");
	}
	

}
