package com.thevolume360.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component("applicationContextProvider")
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext() {

		return context;
	}
	
	public static ObjectMapper getObjectMapper(){
		return (ObjectMapper) context.getBean("objectMapper");
	}
	
	public static ObjectWriter getObjectWriter(){
		return (ObjectWriter) context.getBean("objectWriter");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}
