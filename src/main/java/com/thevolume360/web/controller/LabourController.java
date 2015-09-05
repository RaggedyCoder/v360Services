package com.thevolume360.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.Labour;
import com.thevolume360.domain.enums.Gender;
import com.thevolume360.service.LabourService;
import com.thevolume360.utils.PageWrapper;
import com.thevolume360.web.editor.GenderEditor;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour")
public class LabourController {
	private static final Logger log = LoggerFactory.getLogger(LabourController.class);

	@Autowired
	private LabourService labourService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Gender.class, new GenderEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Labour labour, Model uiModel) {

		return "labour/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Valid Labour labour, BindingResult result, RedirectAttributes redirectAttributes) {
		validateLabour(labour, result);
		if (result.hasErrors()) {

			return "labour/create";
		}
		labourService.create(labour);
		redirectAttributes.addFlashAttribute("message", String.format("Labour successfully created"));
		return "redirect:/labour/show/" + labour.getId().toString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model uiModel) {

		Labour selectedLabour = labourService.findOne(id);
		uiModel.addAttribute("labour", selectedLabour);

		return "labour/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid Labour labour, BindingResult result, RedirectAttributes redirectAttributes) {

		validateLabour(labour, result);

		if (result.hasErrors()) {

			return "labour/edit";
		}

		labourService.update(labour);

		redirectAttributes.addFlashAttribute("message", String.format("Labour successfully updated"));

		return "redirect:/labour/show/" + labour.getId().toString();
	}

	@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {

		Page<Labour> labours = labourService.findAll(pageable);
		PageWrapper<Labour> page = new PageWrapper<>(labours, "/labour/list");
		uiModel.addAttribute("page", page);

		return "labour/index";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {

		Labour labour = labourService.findOne(id);
		uiModel.addAttribute("labour", labour);
		System.out.println(labour);
		return "labour/show";
	}

	@RequestMapping(value = "details/{id}", method = RequestMethod.GET)
	public String details(@PathVariable("id") Long id, Model uiModel) {

		uiModel.addAttribute("labour", labourService.findOne(id));

		return "labour/details";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		log.debug("cancel()");

		return "redirect:/labour/list";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(Model uiModel) {
		log.debug("search()");

		uiModel.addAttribute("labourSearchCmd", new LabourSearchCmd());

		return "labour/search";
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String display(@ModelAttribute("labourSearchCmd") LabourSearchCmd labourSearchCmd, Pageable pageable,
			Model uiModel, HttpServletRequest request) {
		log.info("display() labourSearchCmd ={}", labourSearchCmd);

		if (isEmpty(labourSearchCmd.getFullName().getFirstName())
				&& isEmpty(labourSearchCmd.getFullName().getLastName()) && isEmpty(labourSearchCmd.getContactNumber())
				&& labourSearchCmd.getGender() != null) {

			uiModel.addAttribute("labourSearchCmd", labourSearchCmd);
			uiModel.addAttribute("error", "Please enter name or Phone Number or gender");

			return "labour/search";
		}
		Page<Labour> labours = labourService.findLabourBySearchCmd(labourSearchCmd, pageable);
		if (labours == null || labours.getTotalElements() == 0) {
			uiModel.addAttribute("labourSearchCmd", labourSearchCmd);
			uiModel.addAttribute("notFound", "The labour Information you are looking for, doesn't exist!");

			return "labour/search";
		}
		PageWrapper<Labour> page = new PageWrapper<>(labours, "/labour/display?" + request.getQueryString());
		uiModel.addAttribute("page", page);
		/*
		 * if (labours.getTotalElements() == 0) {
		 * 
		 * uiModel.addAttribute("labourSearchCmd", labourSearchCmd);
		 * uiModel.addAttribute("notFound",
		 * "The labour Information you are looking for, doesn't exist!");
		 * 
		 * return "labour/search"; }
		 * 
		 * PageWrapper<Labour> page = new PageWrapper<>(labours,
		 * "/labour/display?" + request.getQueryString());
		 * uiModel.addAttribute("page", page);
		 */

		return "labour/index";
	}

	private boolean isEmpty(String string) {
		return string.length() != 0;
	}

	private void validateLabour(Labour labour, BindingResult result) {

	}
}
