package com.thevolume360.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.thevolume360.domain.LabourWorkInfo;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.domain.ProjectLabour;
import com.thevolume360.domain.enums.WageType;
import com.thevolume360.service.LabourService;
import com.thevolume360.service.LabourWageInfoService;
import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.service.ProjectLabourService;
import com.thevolume360.web.editor.WageTypeEditor;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/projects/labour")
public class ProjectLabourController {
	static final Logger LOG = LoggerFactory.getLogger(ProjectLabourController.class);

	@Autowired
	private LabourService labourService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@Autowired
	private ProjectLabourService projectLabourService;

	@Autowired
	private LabourWageInfoService labourWageInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(WageType.class, new WageTypeEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "intake/{id}", method = RequestMethod.GET)
	public String create(@PathVariable Long id, Model uiModel, Pageable pageable) {
		ProjectInfo projectInfo = null;
		try {
			projectInfo = projectInfoService.findOne(id);
		} catch (Exception e) {
			System.out.println("error");
		}
		List<Labour> labours = new ArrayList<Labour>();
		try {
			labours = labourService.getLaboursNotInThisProject(projectInfo);
			System.out.println("sucess");
			System.out.println(labours.size());
		} catch (Exception e) {
			System.out.println("error");
		}
		uiModel.addAttribute("labours", labours);
		uiModel.addAttribute("projectInfo", projectInfo);
		IntakeLabours intakeLabours = new IntakeLabours();
		uiModel.addAttribute("intakeLabours", intakeLabours);
		return "projects/labour/intake";
	}

	@RequestMapping(value = "complete/{id}", method = RequestMethod.POST)
	public String create(@PathVariable Long id, IntakeLabours intakeLabours, BindingResult result, Model uiModel) {
		System.out.println(intakeLabours.getLabours().size());
		if (intakeLabours.getLabours().size() <= 0) {
			ProjectInfo projectInfo = null;
			try {
				projectInfo = projectInfoService.findOne(id);
				List<Labour> labours = labourService.findAll();
				uiModel.addAttribute("labours", labours);
				uiModel.addAttribute("projectInfo", projectInfo);
			} catch (Exception e) {
				System.out.println("error");
			}
			return "redirect:/projects/labour/intake/" + id;
		}
		try {
			System.out.println(intakeLabours);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
		}
		ProjectInfo projectInfo = null;

		try {
			projectInfo = projectInfoService.findOne(id);
			for (Labour dummyLabour : intakeLabours.getLabours()) {
				Labour labour = labourService.findOne(dummyLabour.getId());
				System.out.println("taking labour-" + labour);

				ProjectLabour projectLabour = new ProjectLabour();
				projectLabour.setLabour(labour);
				projectLabour.setProjectJoinDate(new Date(System.currentTimeMillis()));
				projectInfo.setProjectLabours(null);
				projectLabour.setProjectInfo(projectInfo);
				System.out.println("project labour-");

				LabourWageInfo labourWageInfo = new LabourWageInfo();
				System.out.println(1);
				labourWageInfo.setWageType(
						(intakeLabours.getWageType() == null) ? WageType.DAILY_TYPE : intakeLabours.getWageType());
				System.out.println(2);
				labourWageInfo.setWageUnit(intakeLabours.getWageUnit());
				System.out.println(3);
				labourWageInfo.setProjectLabour(projectLabour);
				System.out.println("labour wage");
				try {
					System.out.println("controller sent");
					projectLabourService.create(projectLabour);
					System.out.println("problem");
				} catch (Exception e) {
					System.out.println("error");
					System.out.println(e.getClass().getSimpleName());
				}
				System.out.println("project labour created");
				labourWageInfoService.create(labourWageInfo);

			}
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
		}
		projectInfo = projectInfoService.findOne(id);
		uiModel.addAttribute("projectInfo", projectInfo);
		return "redirect:/projects/show/" + id;
	}

	@RequestMapping(value = "cancel/{id}", method = RequestMethod.GET)
	public String cancel(@PathVariable Long id) {
		return "redirect:/projects/show/" + id;
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {
		ProjectLabour projectLabour = projectLabourService.findOne(id);
		Integer wagePaid = 0;
		Integer extraPaid = 0;
		Integer paymentNeeded = 0;
		Integer netPayment = 0;
		for (LabourWorkInfo labourWorkInfo : projectLabour.getLabourWorkInfos()) {
			if (labourWorkInfo.getIsPaid()) {
				wagePaid += labourWorkInfo.getLabourPaymentInfo().getNormalPaidAmount();
				extraPaid += labourWorkInfo.getLabourPaymentInfo().getExtraPaidAmount();

			} else {
				for (LabourWageInfo labourWageInfo : projectLabour.getLabourWageInfos()) {
					if (labourWageInfo.getLastValidDate() == null) {
						if (labourWageInfo.getWageType().equals(WageType.MONTHLY_TYPE)) {
							paymentNeeded += (labourWageInfo.getWageUnit() / 26);
						} else {
							paymentNeeded += (labourWageInfo.getWageUnit() * labourWorkInfo.getWorkedHour());
						}
					}
				}
			}
			netPayment = wagePaid + extraPaid - paymentNeeded;
		}
		uiModel.addAttribute("wagePaid", wagePaid);
		uiModel.addAttribute("extraPaid", extraPaid);
		uiModel.addAttribute("paymentNeeded", paymentNeeded);
		uiModel.addAttribute("netPayment", netPayment);
		uiModel.addAttribute("projectLabour", projectLabour);
		return "projects/labour/show";

	}
}
