package com.thevolume360.web.controller.project.labour;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.domain.IntakeLabours;
import com.thevolume360.domain.Labour;
import com.thevolume360.domain.LabourWageInfo;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.domain.ProjectLabour;
import com.thevolume360.domain.enums.WageCategory;
import com.thevolume360.service.LabourService;
import com.thevolume360.service.LabourWageInfoService;
import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.service.ProjectLabourService;
import com.thevolume360.service.WageTypeService;
import com.thevolume360.web.editor.WageCategoryEditor;

@Controller(value = "projectLabourCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project/labour")
public class CreationController {

	static final Logger LOG = LoggerFactory.getLogger(CreationController.class);

	@Autowired
	private LabourService labourService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@Autowired
	private ProjectLabourService projectLabourService;

	@Autowired
	private LabourWageInfoService labourWageInfoService;

	@Autowired
	private WageTypeService wageTypeService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		binder.registerCustomEditor(WageCategory.class, new WageCategoryEditor());
	}

	@RequestMapping(value = "intake/{id}", method = RequestMethod.GET)
	public String create(@PathVariable Long id, Model uiModel, Pageable pageable) {
		ProjectInfo projectInfo = projectInfoService.findOne(id);
		uiModel.addAttribute("labours", labourService.getLaboursNotInThisProject(projectInfo));
		uiModel.addAttribute("wageTypes", wageTypeService.findAll());
		uiModel.addAttribute("projectInfo", projectInfo);
		uiModel.addAttribute("intakeLabours", new IntakeLabours());
		return "project/labour/intake";
	}

	@RequestMapping(value = "complete/{id}", method = RequestMethod.POST)
	public String create(@PathVariable Long id, IntakeLabours intakeLabours, BindingResult result, Model uiModel) {
		if (!checkValidity(intakeLabours)) {
			return "redirect:/project/labour/intake/" + id;
		}
		ProjectInfo projectInfo = projectInfoService.findOne(id);
		for (Labour labour : intakeLabours.getLabours()) {
			ProjectLabour projectLabour = new ProjectLabour();
			projectLabour.setLabour(labour);
			projectLabour.setProjectInfo(projectInfo);
			LabourWageInfo labourWageInfo = new LabourWageInfo();
			labourWageInfo.setWageType(intakeLabours.getWageType());
			labourWageInfo.setWageUnit(intakeLabours.getWageUnit());
			labourWageInfo.setProjectLabour(projectLabour);
			projectLabourService.create(projectLabour);
			labourWageInfoService.create(labourWageInfo);
		}
		return "redirect:/project/show/" + id;
	}

	private boolean checkValidity(IntakeLabours intakeLabours) {
		return intakeLabours.getLabours().size() > 0;
	}

}
