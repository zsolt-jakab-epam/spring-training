package com.jazs.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jazs.spring.aop.camera.accessories.Lens;

public class App {

	public static void main(String[] args) {
	    try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/jazs/spring/aop/beans.xml")) {
	    	
	    	Camera camera = (Camera) context.getBean("camera");
	    	Car car = (Car) context.getBean("car");
	    	
	    	camera.snap();
	    	camera.snap(500);
	    	camera.snapNighttime();
	    	
	    	camera.snap(car);
	    	
	    	car.start();
	    	
	    }
	}

}
