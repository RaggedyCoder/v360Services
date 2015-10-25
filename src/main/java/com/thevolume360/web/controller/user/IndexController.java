package com.thevolume360.web.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.domain.User;
import com.thevolume360.service.UserService;
import com.thevolume360.utils.PageWrapper;

@Controller("userIndexController")
@RequestMapping("/user")
@Secured("ROLE_ADMIN")
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {

		Page<User> users = userService.findAll(pageable);
		PageWrapper<User> page = new PageWrapper<>(users, "/user");
		uiModel.addAttribute("page", page);

		return "user/index";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		log.debug("show() id ={}", id);

		User user = userService.findById(id);
		uiModel.addAttribute("user", user);

		return "user/show";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		log.debug("cancel()");

		return "redirect:/user";
	}
}
