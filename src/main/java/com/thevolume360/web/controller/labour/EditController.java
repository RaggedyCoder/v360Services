package com.thevolume360.web.controller.labour;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.Labour;
import com.thevolume360.service.LabourService;

@Controller("labourEditController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour")
public class EditController {

	@Autowired
	private LabourService labourService;

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model uiModel) {

		Labour selectedLabour = labourService.findOne(id);
		uiModel.addAttribute("labour", selectedLabour);

		return "labour/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid Labour labour, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {

			return "labour/edit";
		}

		labourService.update(labour);

		redirectAttributes.addFlashAttribute("message", String.format("Labour successfully updated"));

		return "redirect:/labour/show/" + labour.getId();
	}

}
