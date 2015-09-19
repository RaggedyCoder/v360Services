package com.thevolume360.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.LabourWorkInfo;
import com.thevolume360.service.LabourWorkInfoService;
import com.thevolume360.service.ProjectLabourService;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour/work/info")
public class LabourWorkInfoController {

	@Autowired
	private ProjectLabourService projectLabourService;

	@Autowired
	private LabourWorkInfoService labourWorkInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Param("id") String id, Model uiModel) {
		uiModel.addAttribute("projectLabourId", id);
		uiModel.addAttribute("labourWorkInfo", new LabourWorkInfo());
		return "labour/work/info/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(String projectLabourId, LabourWorkInfo labourWorkInfo, Model uiModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		labourWorkInfo.setProjectLabour(projectLabourService.findOne(Long.parseLong(projectLabourId)));
		System.out.println(labourWorkInfo);
		labourWorkInfoService.create(labourWorkInfo);
		return "redirect:/project/labour/show/" + projectLabourId;
	}

}