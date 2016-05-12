package com.thevolume360.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.thevolume360.domain.User;
import com.thevolume360.service.UserService;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {
	private static final Logger log = LoggerFactory
			.getLogger(SpringSecurityAuditorAware.class);

	@Autowired
	private UserService userService;

	public User getCurrentAuditor() {
		log.info("getCurrentAuditor()");

		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return userService.findById(user.getId());
	}

}
