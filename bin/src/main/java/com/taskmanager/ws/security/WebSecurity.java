package com.taskmanager.ws.security;



//package com.taskmanager.ws.security;
//
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import com.taskmanager.ws.service.UserService;
//
//

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.taskmanager.ws.service.UserService;






@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
//
    private final UserService userDetailsService;
////    private final BCryptPasswordEncoder bCryptPasswordEncoder;
////
    public WebSecurity(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    
    @Override
	protected void configure(HttpSecurity http) throws Exception{
         http.
         cors().and().
         csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
        .antMatchers(HttpMethod.PUT, SecurityConstants.PASSWORD_RESET_URL).permitAll()
        .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**","/webjars/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.addFilter(getAutheticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager())).sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
    }

	PasswordEncoder encoder =
		     PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);
	}

	public AuthenticationFilter getAutheticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}

}
