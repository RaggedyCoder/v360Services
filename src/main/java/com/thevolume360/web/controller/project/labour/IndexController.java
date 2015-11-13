package com.thevolume360.web.controller.project.labour;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.domain.LabourWageInfo;
import com.thevolume360.domain.LabourWorkInfo;
import com.thevolume360.domain.ProjectLabour;
import com.thevolume360.domain.enums.WageCategory;
import com.thevolume360.service.LabourWorkInfoService;
import com.thevolume360.service.ProjectLabourService;

@Controller(value = "projectLabourIndexController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project/labour")
public class IndexController {

	static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private ProjectLabourService projectLabourService;

	@Autowired
	private LabourWorkInfoService labourWorkInfoService;

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {
		ProjectLabour projectLabour = projectLabourService.findOne(id);
		labourWageCalculation(uiModel, projectLabour);
		uiModel.addAttribute("projectLabour", projectLabour);
		return "project/labour/show";
	}

	private void labourWageCalculation(Model uiModel, ProjectLabour projectLabour) {
		Integer wagePaid = 0;
		Integer extraPaid = 0;
		Integer paymentNeeded = 0;
		Integer netPayment = 0;
		for (LabourWageInfo labourWageInfo : projectLabour.getLabourWageInfos()) {

			List<LabourWorkInfo> filteredLabourWorkInfo;
			if (labourWageInfo.getLastValidDate() != null) {
				filteredLabourWorkInfo = labourWorkInfoService.findLabourWorkInfosByActivationDateAndLastValidDate(
						projectLabour.getId(), labourWageInfo.getActivationDate(), labourWageInfo.getLastValidDate());

			} else {
				filteredLabourWorkInfo = labourWorkInfoService
						.findLabourWorkInfosByActivationDate(projectLabour.getId(), labourWageInfo.getActivationDate());
			}
			System.out.println(filteredLabourWorkInfo.size());
			for (LabourWorkInfo labourWorkInfo : filteredLabourWorkInfo) {
				System.out.println(labourWorkInfo);
				if (labourWorkInfo.getIsPaid()) {
					wagePaid += labourWorkInfo.getLabourPaymentInfo().getNormalPaidAmount();
					extraPaid += labourWorkInfo.getLabourPaymentInfo().getExtraPaidAmount();
				} else {
					if (labourWageInfo.getWageType().getWageCategory().equals(WageCategory.SUMMATION_UNIT)) {
						paymentNeeded += (labourWageInfo.getWageUnit() / 26);
					} else {
						paymentNeeded += (labourWageInfo.getWageUnit() * labourWorkInfo.getWorkedUnit());
					}
				}
			}
		}
		netPayment = wagePaid + extraPaid - paymentNeeded;
		System.out.println("wagePaid- "+ wagePaid);
		System.out.println("extraPaid- "+ extraPaid);
		System.out.println("paymentNeeded- "+ paymentNeeded);
		System.out.println("netPayment- "+ netPayment);

		uiModel.addAttribute("wagePaid", wagePaid);
		uiModel.addAttribute("extraPaid", extraPaid);
		uiModel.addAttribute("paymentNeeded", paymentNeeded);
		uiModel.addAttribute("netPayment", netPayment);

	}

	@RequestMapping(value = "cancel/{id}", method = RequestMethod.GET)
	public String cancel(@PathVariable Long id) {
		return "redirect:/project/show/" + id;
	}

}
