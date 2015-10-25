package com.thevolume360.web.controller.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.User;
import com.thevolume360.domain.enums.Role;
import com.thevolume360.service.UserService;
import com.thevolume360.web.editor.AuthorityEditor;

@Controller("userCreationController")
@RequestMapping("/user")
@Secured("ROLE_ADMIN")
public class CreationController {

	private static final Logger log = LoggerFactory.getLogger(CreationController.class);

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Role.class, new AuthorityEditor());
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(User user) {

		return "user/create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String save(@Valid User user, BindingResult result, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {

			return "user/create";
		}

		User userFound = userService.findByUserName(user.getUsername());
		if (userFound != null) {
			result.rejectValue("username", "error.user.username.already.available",
					"Its look like someone already has that username. Try another");
			return "user/create";
		}

		userService.save(user);

		redirectAttrs.addFlashAttribute("message", "Successfully user created");

		return "redirect:/user/show/" + user.getId().toString();
	}
}
