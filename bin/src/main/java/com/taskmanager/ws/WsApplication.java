package com.taskmanager.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.taskmanager.ws.security.AppPrpoerties;
import com.taskmanager.ws.security.SpringSecurityAuditorAware;






@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableJpaAuditing
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}
	
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WsApplication.class);
	}
	
	
    @Bean
    SpringApplicationContext applicationContext() {
		return new SpringApplicationContext();
	}
    
    
    @Bean(name = "AppProperties")
    AppPrpoerties appProperties() {
		return new AppPrpoerties();
	}

//
    @Bean
    AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder () {
//		return new BCryptPasswordEncoder();
//	}
    
    @Bean
    static NoOpPasswordEncoder passwordEncoder() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}


}
