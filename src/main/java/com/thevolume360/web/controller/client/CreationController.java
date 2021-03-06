package com.thevolume360.web.controller.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.Client;
import com.thevolume360.domain.enums.ClientType;
import com.thevolume360.service.ClientService;
import com.thevolume360.web.editor.ClientTypeEditor;

@Controller("clientCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/client")
public class CreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CreationController.class);

	@Autowired
	private ClientService clientService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(ClientType.class, new ClientTypeEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Client client, Model uiModel) {
		LOG.debug("create(Client client, Model uiModel)");
		LOG.info("display() client ={}", client);
		return "client/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, RedirectAttributes redirectAttributes) {
		LOG.debug("save(@Valid Client client, BindingResult result," + "RedirectAttributes redirectAttributes)");
		LOG.info("display() client ={}", client);
		LOG.info("display() errors ={}", result.getAllErrors());
		if (result.hasErrors()) {
			return "client/create";
		}
		clientService.create(client);
		redirectAttributes.addFlashAttribute("message", String.format("Client details successfully created"));
		return "redirect:/client/list";
	}

}
