package com.thevolume360.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.UserDao;
import com.thevolume360.domain.User;
import com.thevolume360.service.UserService;
import com.thevolume360.utils.StringUtils;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	@Override
	public void save(User user) {
		if (user.getId() == null) {
			user.setUsername(user.getUsername().trim());
			user.setSalt(StringUtils.generateRandomString(16));
			user.setHashedPassword(messageDigestPasswordEncoder.encodePassword(user.getPassword(), user.getSalt()));
			user.setEnabled(true);
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);

			userDao.save(user);
		} else {
			User savedUser = findById(user.getId());

			savedUser.setFullName(user.getFullName());
			savedUser.setEmail(user.getEmail());
			savedUser.setRoles(user.getRoles());
			savedUser.setDesignation(user.getDesignation());
			savedUser.setPhoneNumber(user.getPhoneNumber());

			String encryptedPassword = messageDigestPasswordEncoder.encodePassword(user.getPassword(),
					savedUser.getSalt());
			savedUser.setHashedPassword(encryptedPassword);

			userDao.save(savedUser);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		System.out.println(username);
		if (user == null) {
			System.out.println("error");
			throw new UsernameNotFoundException("User not found by " + username);
		}

		return user;
	}

	@Override
	public User findByUserName(String username) {
		System.out.println(username);
		try {

			return userDao.findByUsername(username);
		} catch (NoResultException e) {
			log.error("user not found by ={}", username, e);
		}

		return null;
	}

	@Override
	public User findById(Long id) {

		return userDao.findOne(id);
	}

	@Override
	public User getCurrentLoggedInUser() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("getCurrentLoggedInUser() => user.getUsername() ={}, id={}", user.getUsername(), user.getId());

		return findById(user.getId());
	}

	@Override
	public Long count() {

		return userDao.count();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {

		return userDao.findAll(pageable);
	}
}
