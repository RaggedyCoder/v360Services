package com.thevolume360.web.controller.bank.withdraw;

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

import com.thevolume360.domain.BankWithdraw;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.service.BankWithdrawService;
import com.thevolume360.service.ProjectInfoService;

@Controller("bankWithdrawCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/bank/withdraw")
public class CreationController {
	private static final String TAG = CreationController.class.getSimpleName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CreationController.class);
	@Autowired
	private BankWithdrawService bankWithdrawService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create/{projectInfoId}", method = RequestMethod.GET)
	public String create(@PathVariable("projectInfoId") Long projectInfoId, Model uiModel) {
		LOGGER.debug(DEBUG_METHOD_ENTER, "create(@PathVariable(\"projectInfoId\") Long projectInfoId, Model uiModel)",
				TAG);
		ProjectInfo projectInfo = projectInfoService.findOne(projectInfoId);
		if (projectInfo != null) {
			uiModel.addAttribute("projectInfo", projectInfo);
			uiModel.addAttribute("bankWithdraw", new BankWithdraw());
			return "bank/withdraw/create";
		} else {
			return "redirect:/admin/index";
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("projectInfo") ProjectInfo projectInfo,
			@RequestParam("bankWithdraw") BankWithdraw bankWithdraw, Model uiModel) {
		LOGGER.debug(DEBUG_METHOD_ENTER, "save(@RequestParam(\"projectInfo\") ProjectInfo projectInfo,"
				+ " @RequestParam(\"bankWithdraw\") BankWithdraw bankWithdraw," + " Model uiModel)", TAG);
		// ProjectInfo projectInfo = projectInfoService.findOne(projectInfo);
		return "redirect:/project/show/" + projectInfo.getId();
	}
}
