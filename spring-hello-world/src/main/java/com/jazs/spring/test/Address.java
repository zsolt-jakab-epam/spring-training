package com.jazs.spring.test;

public class Address {

	private String street;
	private String postCode;

	public Address(String street, String postCode) {
		super();
		this.street = street;
		this.postCode = postCode;
	}
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", postCode=" + postCode + "]";
	}
}
