package com.jazs.spring.test;

public class PersonFactory {
	
	public Person createPerson(int id, String name, Address address) {
		System.out.println("Using factory to create person.");
		return new Person(id, name, address);
	} 
}
