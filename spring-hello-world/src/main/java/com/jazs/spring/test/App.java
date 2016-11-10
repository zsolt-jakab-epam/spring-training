package com.jazs.spring.test;

import java.nio.file.FileSystem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		
		Person person1 = (Person)context.getBean("person");
		Person person2 = (Person)context.getBean("person");
		
		System.out.println(person2);
		
		((FileSystemXmlApplicationContext)context).close();
	}

}
