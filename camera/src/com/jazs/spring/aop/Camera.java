package com.jazs.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")
public class Camera {
	
	public void snap() {
		System.out.println("SNAP!");
	}
	
	public void snap(int exposure) {
		System.out.println("SNAP! Exposure: " + exposure);
	}
	
	public String snap(String name) {
		System.out.println("SNAP! Exposure: " + name);
		
		return name;
	}
	
	public void snapNighttime() {
		System.out.println("SNAP! Night mode.");
	}
	
	public void snap(Car car) {
		System.out.println("Snapping car");
	}
}
