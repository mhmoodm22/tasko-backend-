package com.taskmanager.ws.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppPrpoerties {
	
	@Autowired
	private Environment env;
 
	public String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
}
