package com.thevolume360.web.controller.project.labour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.domain.ProjectLabour;
import com.thevolume360.service.ProjectLabourService;

@Controller(value = "projectLabourIndexController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project/labour")
public class IndexController {

	static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private ProjectLabourService projectLabourService;

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {
		ProjectLabour projectLabour = projectLabourService.findOne(id);
		Integer wagePaid = 0;
		Integer extraPaid = 0;
		Integer paymentNeeded = 0;
		Integer netPayment = 0;
		// for (LabourWorkInfo labourWorkInfo :
		// projectLabour.getLabourWorkInfos()) {
		// if (labourWorkInfo.getIsPaid()) {
		// wagePaid +=
		// labourWorkInfo.getLabourPaymentInfo().getNormalPaidAmount();
		// extraPaid +=
		// labourWorkInfo.getLabourPaymentInfo().getExtraPaidAmount();
		//
		// } else {
		// for (LabourWageInfo labourWageInfo :
		// projectLabour.getLabourWageInfos()) {
		// if (labourWageInfo.getLastValidDate() == null) {
		// if (labourWageInfo.getWageType().getName().equals("Monthly")) {
		// paymentNeeded += (labourWageInfo.getWageUnit() / 26);
		// } else {
		// paymentNeeded += (labourWageInfo.getWageUnit() *
		// labourWorkInfo.getWorkedHour());
		// }
		// }
		// }
		// }
		// netPayment = wagePaid + extraPaid - paymentNeeded;
		// }
		uiModel.addAttribute("wagePaid", wagePaid);
		uiModel.addAttribute("extraPaid", extraPaid);
		uiModel.addAttribute("paymentNeeded", paymentNeeded);
		uiModel.addAttribute("netPayment", netPayment);
		uiModel.addAttribute("projectLabour", projectLabour);
		return "project/labour/show";
	}

	@RequestMapping(value = "cancel/{id}", method = RequestMethod.GET)
	public String cancel(@PathVariable Long id) {
		return "redirect:/project/show/" + id;
	}

}
