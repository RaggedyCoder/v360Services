package com.thevolume360.web.controller.project.buying;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.service.ProjectBuyingService;
import com.thevolume360.service.ProjectInfoService;

@Controller("projectBuyingCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project/buying")
public class CreationController {
	private static final Logger LOG = LoggerFactory.getLogger(CreationController.class);
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
		LOG.debug("createReceipt(String id, ProjectBuying projectBuying, Model uiModel)");
		projectBuying.setBoughtDate(new Date());
		projectBuying.setProjectInfo(projectInfoService.findOne(Long.parseLong(id)));
		uiModel.addAttribute("projectBuying", projectBuying);
		return getViewPath("create");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String updateReceipt(Boolean addMaterial, ProjectBuying projectBuying, Model uiModel,
			RedirectAttributes redirectAttributes) {
		ProjectInfo projectInfo = projectInfoService.findOne(projectBuying.getProjectInfo().getId());
		projectInfo.setProjectBuyings(null);
		projectBuying.setProjectInfo(projectInfo);
		try {
			ProjectBuying newProjectBuying = projectBuyingService.create(projectBuying);
			if (addMaterial) {
				return "redirect:/project/material/create/" + newProjectBuying.getId();
			} else {
				return "redirect:/project/show/" + projectInfo.getId();
			}
		} catch (Exception e) {
			return "redirect:/project/show/" + projectInfo.getId();
		}
	}

	private String getViewPath(String path) {
		return String.format("project/buying/%s", path);
	}

}
