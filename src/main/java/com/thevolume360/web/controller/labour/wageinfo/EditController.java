package com.thevolume360.web.controller.labour.wageinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.thevolume360.domain.ProjectLabour;
import com.thevolume360.domain.update.RenewLabourWageInfo;
import com.thevolume360.service.LabourWageInfoService;
import com.thevolume360.service.ProjectLabourService;
import com.thevolume360.service.WageTypeService;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour/wage/info")
public class EditController {

	private static final Logger LOG = LoggerFactory.getLogger(EditController.class);
	@Autowired
	private ProjectLabourService projectLabourService;
	@Autowired
	private LabourWageInfoService labourWageInfoService;

	@Autowired
	private WageTypeService wageTypeService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/renew", method = RequestMethod.GET)
	public String renew(@Param("id") String id, Model uiModel) {
		LOG.debug("renew(@Param(\"id\") String id, Model uiModel)");
		LOG.info("display() id ={}", id);
		RenewLabourWageInfo renewLabourWageInfo = new RenewLabourWageInfo(Long.parseLong(id));
		uiModel.addAttribute("wageTypes", wageTypeService.findAll());
		uiModel.addAttribute("renewLabourWageInfo", renewLabourWageInfo);
		System.out.println(renewLabourWageInfo);
		return "labour/wage/info/renew";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String save(@Valid RenewLabourWageInfo renewLabourWageInfo, BindingResult bindingResult, Model uiModel,
			RedirectAttributes redirectAttributes) {
		LOG.debug("save(@Valid RenewLabourWageInfo renewLabourWageInfo, BindingResult bindingResult,"
				+ " Model uiModel,RedirectAttributes redirectAttributes");
		LOG.info("display() renewLabourWageInfo ={}", renewLabourWageInfo);
		if (!checkValidity(bindingResult, renewLabourWageInfo)) {
			LOG.debug("redirecting: url ={}", "/labour/wage/info/renew");
			redirectAttributes.addFlashAttribute("id", renewLabourWageInfo.getProjectLabourId());
			return "redirect:/labour/wage/info/renew";
		}

		ProjectLabour projectLabour = projectLabourService.findOne(renewLabourWageInfo.getProjectLabourId());
		for (LabourWageInfo oldLabourWageInfo : projectLabour.getLabourWageInfos()) {
			if (oldLabourWageInfo.getLastValidDate() == null) {
				oldLabourWageInfo.setLastValidDate(renewLabourWageInfo.getLastWageInfoExpiredDate());
				try {
					labourWageInfoService.update(oldLabourWageInfo);
				} catch (Exception e) {
					LOG.error("Exception: type={} message ={}", e.getClass().getSimpleName(), e.getMessage());
				}
				break;
			}
		}
		LabourWageInfo labourWageInfo = renewLabourWageInfo.getLabourWageInfo();
		labourWageInfo.setProjectLabour(projectLabour);
		labourWageInfoService.create(labourWageInfo);
		redirectAttributes.addFlashAttribute("message", "Labour Wage Info was updated succesfully");
		LOG.debug("redirecting: url ={}", "/project/labour/show/" + renewLabourWageInfo.getProjectLabourId());
		return "redirect:/project/labour/show/" + renewLabourWageInfo.getProjectLabourId();
	}

	private boolean checkValidity(BindingResult bindingResult, RenewLabourWageInfo renewLabourWageInfo) {
		return !bindingResult.hasErrors() || renewLabourWageInfo.getLabourWageInfo().getWageUnit() != null
				|| renewLabourWageInfo.getLabourWageInfo().getWageUnit() > 0
				|| renewLabourWageInfo.getLabourWageInfo().getWageType() != null
				|| !renewLabourWageInfo.getLabourWageInfo().getWageType().getName().isEmpty();
	}
}
