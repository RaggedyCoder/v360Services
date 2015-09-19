package com.thevolume360.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.ProjectBuying;
import com.thevolume360.service.ProjectBuyingService;
import com.thevolume360.service.ProjectInfoService;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project/buying")
public class ProjectBuyingController {

	@Autowired
	private ProjectBuyingService projectBuyingService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createReceipt(String id, ProjectBuying projectBuying, Model uiModel) {
		// projectBuyingService.create(projectBuying);
		// System.out.println(id);
		uiModel.addAttribute("projectBuying", projectBuying);
		uiModel.addAttribute("projectInfo", projectInfoService.findOne(Long.parseLong(id)));
		return getViewPath("create");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String updateReceipt(String projectInfoId, ProjectBuying projectBuying, Model uiModel,
			RedirectAttributes redirectAttributes) {
		return "redirect:/project/material/create";
	}

	// @RequestMapping(value = "/create", method = RequestMethod.POST)
	// public String completeReceipt(ProjectBuying projectBuying, Model uiModel)
	// {
	// return getViewPath("create");
	// }

	private String getViewPath(String path) {
		return String.format("project/buying/%s", path);
	}
}
