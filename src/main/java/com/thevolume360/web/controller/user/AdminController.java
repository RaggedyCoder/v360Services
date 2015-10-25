package com.thevolume360.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.service.ClientService;
import com.thevolume360.service.LabourService;
import com.thevolume360.service.OfficeWorkerService;
import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.service.UserService;

@Controller("userAdminController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
public class AdminController {

	@Autowired
	private LabourService labourService;
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private OfficeWorkerService officeWorkerService;

	@RequestMapping(value = { "admin/index", "/" }, method = RequestMethod.GET)
	private String index(Model uiModel) {
		uiModel.addAttribute("totalUser", userService.count());
		uiModel.addAttribute("totalProject", projectInfoService.count());
		uiModel.addAttribute("totalLabour", labourService.count());
		uiModel.addAttribute("totalClient", clientService.count());
		uiModel.addAttribute("totalOfficeWorker", officeWorkerService.count());
		return "admin/index";
	}
}
