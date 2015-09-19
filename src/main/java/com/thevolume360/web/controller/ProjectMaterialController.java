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

import com.thevolume360.domain.ProjectBuying;
import com.thevolume360.service.ProjectBuyingService;
import com.thevolume360.service.ProjectMaterialService;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project/material")
public class ProjectMaterialController {

	@Autowired
	private ProjectMaterialService projectMaterialService;

	@Autowired
	private ProjectBuyingService projectBuyingService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addMaterials(String id, ProjectBuying projectBuying, Model uiModel) {
		// projectBuyingService.create(projectBuying);
		// System.out.println(id);
		return getViewPath("create");
	}

	private String getViewPath(String path) {
		return String.format("project/material/%s", path);
	}

}
