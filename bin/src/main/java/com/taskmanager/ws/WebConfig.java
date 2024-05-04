package com.taskmanager.ws;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.taskmanager.ws.security.SecurityConstants;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {


        registry.addMapping("/**")
        .allowedOrigins("*") // Allow requests from any origin
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow only certain HTTP methods
        .allowedHeaders("*") // Allow all headers
        .exposedHeaders(SecurityConstants.HEADER_STRING, "UserID") // Expose custom headers
        .allowCredentials(true); //

	}
	
}

