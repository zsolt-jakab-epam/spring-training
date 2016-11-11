package com.jazs.spring.test;

public class Address {

	private String street;
	private String postCode;

	public Address() {
	}
	
	public Address(String street, String postCode) {
		this.street = street;
		this.postCode = postCode;
	}
	

	@Override
	public String toString() {
		return "Address [street=" + street + ", postCode=" + postCode + "]";
	}
	
	public void init() {
		System.out.println("Bean created: " + this);
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
