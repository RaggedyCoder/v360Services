package com.thevolume360.web.controller.labour.paymentinfo;

import static com.thevolume360.utils.Constant.DEBUG_METHOD_ENTER;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.LabourPaymentInfo;
import com.thevolume360.domain.LabourWageInfo;
import com.thevolume360.domain.LabourWorkInfo;
import com.thevolume360.service.LabourPaymentInfoService;
import com.thevolume360.service.LabourWageInfoService;
import com.thevolume360.service.LabourWorkInfoService;

@Controller("labourPaymentCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour/payment/info")
public class CreationController {
	private static final String TAG = CreationController.class.getSimpleName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CreationController.class);
	@Autowired
	private LabourPaymentInfoService labourPaymentInfoService;
	@Autowired
	private LabourWorkInfoService labourWorkInfoService;

	@Autowired
	private LabourWageInfoService labourWageInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create/{labourWorkInfoId}", method = RequestMethod.GET)
	public String create(@PathVariable("labourWorkInfoId") Long labourWorkInfoId, LabourPaymentInfo labourPaymentInfo,
			Model uiModel) {
		LOGGER.debug(DEBUG_METHOD_ENTER,
				"create(@PathVariable(\"labourWorkInfoId\") Long labourWorkInfoId, Model uiModel)", TAG);
		LabourWorkInfo labourWorkInfo = null;
		LabourWageInfo labourWageInfo = null;
		try {
			labourWorkInfo = labourWorkInfoService.findOne(labourWorkInfoId);
			labourWageInfo = labourWageInfoService.getLabourWageInfoByWorkedDate(labourWorkInfo.getWorkedDate());
		} catch (Exception e) {

		}
		if (labourWorkInfo == null || labourWageInfo == null) {

		}
		uiModel.addAttribute("labourWorkInfo", labourWorkInfo);
		uiModel.addAttribute("labourWageInfo", labourWageInfo);
		uiModel.addAttribute("labourPaymentInfo", labourPaymentInfo);
		return "labour/payment/info/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("labourWorkInfoId") Long labourWorkInfoId, LabourPaymentInfo labourPaymentInfo,
			RedirectAttributes redirectAttributes, Model uiModel) {
		LOGGER.debug(DEBUG_METHOD_ENTER,
				"save(@RequestParam(\"labourWorkInfoId\") Long labourWorkInfoId,"
						+ " @RequestParam(\"labourPaymentInfo\") LabourPaymentInfo labourPaymentInfo,"
						+ " Model uiModel)",
				TAG);
		LabourWorkInfo labourWorkInfo = labourWorkInfoService.findOne(labourWorkInfoId);
		if (labourPaymentInfo.getNormalPaidAmount() == null || labourPaymentInfo.getNormalPaidAmount() <= 0) {
			// uiModel.addAttribute("labourPaymentInfo", labourPaymentInfo);
			redirectAttributes.addFlashAttribute("labourPaymentInfo", labourPaymentInfo);
			return "redirect:/labour/payment/info/create/" + labourWorkInfo.getId();
		}
		labourPaymentInfo
				.setTotalPaidAmount(labourPaymentInfo.getNormalPaidAmount()
						+ (labourPaymentInfo.getOvertimePaidAmount() != null ? labourPaymentInfo.getOvertimePaidAmount()
								: 0)
						+ (labourPaymentInfo.getExtraPaidAmount() != null ? labourPaymentInfo.getExtraPaidAmount()
								: 0));
		labourPaymentInfo = labourPaymentInfoService.create(labourPaymentInfo);
		labourWorkInfo.setPaid(true);
		labourWorkInfo.setLabourPaymentInfo(labourPaymentInfo);
		labourWorkInfo.setPaid(true);
		labourWorkInfoService.update(labourWorkInfo);
		return "redirect:/project/labour/show/" + labourWorkInfo.getProjectLabour().getId();
	}
}
