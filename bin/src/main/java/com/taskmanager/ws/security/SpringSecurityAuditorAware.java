package com.taskmanager.ws.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

	public Optional<String> getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityContextHolder.getContext().getAuthentication().getCredentials();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		String name = ((String) authentication.getPrincipal());
		return Optional.of(name);
	}
}
