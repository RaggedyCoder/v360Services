package com.thevolume360.web.controller.client.investment;

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

import com.thevolume360.domain.ClientInvestment;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.domain.enums.InvestmentType;
import com.thevolume360.service.ClientInvestmentService;
import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.utils.NonChequeInvestmentIdGenerator;
import com.thevolume360.web.editor.InvestmentTypeEditor;

@Controller("clientInvestmentCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/client/investment")
public class CreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CreationController.class);

	@Autowired
	private ClientInvestmentService clientInvestmentService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(InvestmentType.class, new InvestmentTypeEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = { "id" })
	public String create(ClientInvestment clientInvestment, Model uiModel, @RequestParam("id") String projectId) {
		LOG.debug("create(ClientInvestment clientInvestment, Model uiModel)");
		LOG.info("display() clientInvestment ={}", clientInvestment);
		ProjectInfo projectInfo = projectInfoService.findOne(Long.parseLong(projectId));
		uiModel.addAttribute("projectInfo", projectInfo);
		return "client/investment/create";
	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	public String create(@PathVariable Long id, ClientInvestment clientInvestment, Model uiModel,
			RedirectAttributes redirectAttributes) {
		LOG.debug("create(ClientInvestment clientInvestment, Model uiModel,RedirectAttributes redirectAttributes)");
		LOG.info("display() id ={} clientInvestment ={}", id, clientInvestment);
		clientInvestment.setProjectInfo(projectInfoService.findOne(clientInvestment.getProjectInfo().getId()));
		if (clientInvestment.getInvestmentType().equals(InvestmentType.CASH)) {
			clientInvestment.setUniqueInvestmentCode(
					NonChequeInvestmentIdGenerator.generate(clientInvestment.getClient().getName(),
							clientInvestment.getClient().getClientType(), clientInvestment.getInvestmentDate()));
		}
		try {
			clientInvestmentService.create(clientInvestment);
		} catch (Exception e) {
			LOG.error("Exception: type ={} message ={}", e.getClass().getSimpleName(), e.getMessage());
			System.out.println(e.getMessage());
		}
		return "redirect:/client/show/" + clientInvestment.getClient().getId();
	}
}