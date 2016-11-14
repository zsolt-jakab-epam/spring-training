package com.caveofprogramming.spring.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * Dummy implementation of logger.
 */

public class Logger {

	@Autowired
	private LogWriter consoleWriter;
	
	@Autowired
	private LogWriter fileWriter;

	public void writeFile(String text) {
		fileWriter.write(text);
	}
	
	public void writeConsole(String text) {
		if (consoleWriter != null) {
			consoleWriter.write(text);			
		}
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("destroy");
	}

}
