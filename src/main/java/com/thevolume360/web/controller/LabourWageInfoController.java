package com.thevolume360.web.controller;

import java.text.ParseException;
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

import com.thevolume360.domain.LabourWageInfo;
import com.thevolume360.domain.LabourWorkInfo;
import com.thevolume360.domain.ProjectLabour;
import com.thevolume360.service.ProjectLabourService;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour/wage/info")
public class LabourWageInfoController {

	@Autowired
	private ProjectLabourService projectLabourService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/renew", method = RequestMethod.POST)
	public String renew(@Param("id") String id, Model uiModel) {
		uiModel.addAttribute("projectLabourId", id);
		uiModel.addAttribute("lastWageInfoExpiredDate", new Date());
		uiModel.addAttribute("labourWageInfo", new LabourWageInfo());
		return "labour/wage/info/renew";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String save(String projectLabourId, String lastWageInfoExpiredDate, LabourWageInfo labourWageInfo,
			Model uiModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println(projectLabourId);
		System.out.println(labourWageInfo);
		System.out.println(lastWageInfoExpiredDate);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date lastValidDate = new Date();
		try {
			lastValidDate = simpleDateFormat.parse(lastWageInfoExpiredDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProjectLabour projectLabour = projectLabourService.findOne(Long.parseLong(projectLabourId));
		for (LabourWageInfo lWageInfo : projectLabour.getLabourWageInfos()) {
			if (lWageInfo.getLastValidDate() == null) {
				labourWageInfo.setLastValidDate(lastValidDate);
				
			}
		}
		return "labour/wage/info/renew";
	}

}
