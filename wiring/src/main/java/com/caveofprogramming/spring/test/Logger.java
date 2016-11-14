package com.caveofprogramming.spring.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;

/*
 * Dummy implementation of logger.
 */

@Component
public class Logger {

	
	private LogWriter consoleWriter;
	
	private LogWriter fileWriter;
	
	@Inject
	@Named("consoleWriter")
	public void setConsoleWriter(LogWriter writer) {
		this.consoleWriter = writer;
	}

	@Inject
	@Named("fileWriter")
	public void setFileWriter(LogWriter writer) {
		this.fileWriter = writer;
	}

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
