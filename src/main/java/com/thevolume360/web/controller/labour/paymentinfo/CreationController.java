package com.thevolume360.web.controller.labour.paymentinfo;

import static com.thevolume360.utils.Constant.DEBUG_METHOD_ENTER;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thevolume360.domain.LabourPaymentInfo;
import com.thevolume360.domain.LabourWorkInfo;
import com.thevolume360.service.LabourPaymentInfoService;
import com.thevolume360.service.LabourWorkInfoService;

@Controller("labourPaymentCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour/payment/info")
public class CreationController {
	private static final String TAG = CreationController.class.getSimpleName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CreationController.class);

	private LabourPaymentInfoService labourPaymentInfoService;

	private LabourWorkInfoService labourWorkInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create/{labourWorkInfoId}", method = RequestMethod.GET)
	public String create(@PathVariable("labourWorkInfoId") Long labourWorkInfoId, Model uiModel) {
		LOGGER.debug(DEBUG_METHOD_ENTER,
				"create(@PathVariable(\"labourWorkInfoId\") Long labourWorkInfoId, Model uiModel)", TAG);
		uiModel.addAttribute(labourWorkInfoId);
		uiModel.addAttribute(new LabourPaymentInfo());
		return "labour/payment/info/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("labourWorkInfoId") Long labourWorkInfoId,
			@RequestParam("labourPaymentInfo") LabourPaymentInfo labourPaymentInfo, Model uiModel) {
		LOGGER.debug(DEBUG_METHOD_ENTER,
				"save(@RequestParam(\"labourWorkInfoId\") Long labourWorkInfoId,"
						+ " @RequestParam(\"labourPaymentInfo\") LabourPaymentInfo labourPaymentInfo,"
						+ " Model uiModel)",
				TAG);
		LabourWorkInfo labourWorkInfo = labourWorkInfoService.findOne(labourWorkInfoId);
		return "redirect:/project/labour/show/" + labourWorkInfo.getProjectLabour().getId();
	}
}
