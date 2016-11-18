package com.jazs.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
	    try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/jazs/spring/aop/beans.xml")) {
	    	
	    	Camera camera = (Camera) context.getBean("camera");
	    	
	    	camera.snap();
	    	
	    	
	    }
	}

}
