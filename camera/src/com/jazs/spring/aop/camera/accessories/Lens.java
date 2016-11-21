package com.jazs.spring.aop.camera.accessories;

import org.springframework.stereotype.Component;

@Component
public class Lens {
	
	public void zoom(int factor) {
		System.out.println("Zooming lens: " + factor);
	}

}
