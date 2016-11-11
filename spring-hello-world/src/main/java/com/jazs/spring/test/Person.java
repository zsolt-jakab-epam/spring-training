package com.jazs.spring.test;

public class Person {
	
	private int id;
	
	private String name;
	
	private int taxId;

	private Address address;
	
	public Person() {
	}
	
	public Person(int id, String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public static Person getInstance(int id, String name, Address address) {
		System.out.println("Creating user using factory method.");
		return new Person(1234, "Bob", new Address(null, null));
	}
	
	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public void onCreate() {
		System.out.println("Person is created: " + this);
	}
	
	public void onDestroy() {
		System.out.println("Person is destroyed.");
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", taxId=" + taxId + ", address=" + address + "]";
	}

	public void speak() { 
		System.out.println("Hello! I'm a person.");
	}
	
}
