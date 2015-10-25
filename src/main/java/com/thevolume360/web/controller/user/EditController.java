package com.thevolume360.web.controller.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.User;
import com.thevolume360.service.UserService;

@Controller("userEditController")
@RequestMapping("/user")
@Secured("ROLE_ADMIN")
public class EditController {

	private static final Logger log = LoggerFactory.getLogger(EditController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model uiModel) {
		log.debug("edit() id ={}", id);

		User user = userService.findById(id);
		user.setPassword(null);
		uiModel.addAttribute("user", user);

		return "user/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user, BindingResult result,
			RedirectAttributes redirectAttrs) {
		log.debug("update() user ={}", user);

		if (result.hasErrors()) {
			return "user/edit";
		}

		userService.save(user);
		redirectAttrs.addFlashAttribute("message", "Successfully user updated");

		return "redirect:/user/show/" + user.getId().toString();
	}
}
