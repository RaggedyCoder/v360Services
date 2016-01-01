package com.thevolume360.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.thevolume360.domain.User;
import com.thevolume360.domain.enums.Role;
import com.thevolume360.service.UserService;

@Service("databaseAuthenticationProvider")
public class DatabaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// ignored
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		logger.debug("retrieveUser()  username ={}", username);

		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			throw new BadCredentialsException("Please enter password");
		}

		try {
			User targetUser = (User) userService.loadUserByUsername(username);
			String encryptedPassword = messageDigestPasswordEncoder.encodePassword(password, targetUser.getSalt());
			String expectedPassword = targetUser.getHashedPassword();

			if (targetUser.getRoles() == null || targetUser.getRoles().size() == 0) {
				throw new BadCredentialsException(
						"No role for " + username + " set in database, contact administrator");
			} else {
				for (Role role : targetUser.getRoles()) {
					if (!(role.equals(Role.ROLE_ADMIN) || role.equals(Role.ROLE_USER))) {
						throw new BadCredentialsException(
								"Not expected user role for " + username + " set in database, contact administrator");
					}
				}
			}

			if (!StringUtils.hasText(expectedPassword)) {
				throw new BadCredentialsException(
						"No password for " + username + " set in database, contact administrator");
			}
			if (!encryptedPassword.equals(expectedPassword)) {
				throw new BadCredentialsException("Invalid Password");
			}

			return targetUser;

		} catch (Exception ex) {
			throw new BadCredentialsException("Invalid user");
		}
	}
}
