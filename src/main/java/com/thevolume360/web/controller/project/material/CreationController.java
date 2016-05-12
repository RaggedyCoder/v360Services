package com.thevolume360.web.controller.project.material;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.domain.ProjectBuying;
import com.thevolume360.domain.ProjectMaterial;
import com.thevolume360.domain.enums.MaterialType;
import com.thevolume360.service.MaterialService;
import com.thevolume360.service.ProjectBuyingService;
import com.thevolume360.service.ProjectMaterialService;
import com.thevolume360.web.editor.MaterialTypeEditor;

@Controller("projectMaterialCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project/material")
public class CreationController {
	
	@Autowired
	private ProjectMaterialService projectMaterialService;

	@Autowired
	private ProjectBuyingService projectBuyingService;

	@Autowired
	private MaterialService materialService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		binder.registerCustomEditor(MaterialType.class, new MaterialTypeEditor());

	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String addProjectMaterials(@PathVariable Long id, ProjectMaterial projectMaterial, Model uiModel) {
		uiModel.addAttribute("projectBuying", projectBuyingService.findOne(id));
		uiModel.addAttribute("projectMaterials", projectMaterialService.findAll());
		uiModel.addAttribute("materials", materialService.findAll());
		return getViewPath("create");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProjectMaterials(Boolean addMaterial, String projectBuyingId, ProjectMaterial projectMaterial,
			Model uiModel) {
		// projectBuyingService.create(projectBuying);
		// System.out.println(id);
		ProjectBuying projectBuying = projectBuyingService.findOne(Long.parseLong(projectBuyingId));
		projectBuying.setProjectMaterials(null);
		projectMaterial.setProjectBuying(projectBuying);
		projectMaterial.setTotalCost(projectMaterial.getUnitCost() * projectMaterial.getUnit());
		try {
			projectMaterialService.create(projectMaterial);
			if (addMaterial) {
				return "redirect:/project/material/create/" + projectBuyingId;
			} else {
				return "redirect:/project/show/" + projectBuying.getProjectInfo().getId();
			}
		} catch (Exception e) {
			return "redirect:/project/show/" + projectBuying.getProjectInfo().getId();
		}
	}

	private String getViewPath(String path) {
		return String.format("project/material/%s", path);
	}

}
