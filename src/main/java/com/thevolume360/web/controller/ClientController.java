package com.thevolume360.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.Client;
import com.thevolume360.domain.enums.ClientType;
import com.thevolume360.service.ClientService;
import com.thevolume360.utils.PageWrapper;
import com.thevolume360.web.editor.ClientTypeEditor;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/client")
public class ClientController {
	private static final Logger log = LoggerFactory
			.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		log.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(ClientType.class, new ClientTypeEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Client client, Model uiModel) {
		log.debug("create(Client client, Model uiModel)");
		return "client/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result,
			RedirectAttributes redirectAttributes) {
		log.debug("save(@Valid Client client, BindingResult result,"
				+ "RedirectAttributes redirectAttributes)");

		if (result.hasErrors()) {
			return "client/create";
		}
		clientService.create(client);
		redirectAttributes.addFlashAttribute("message",
				String.format("Client details successfully created"));
		return "redirect:/client/list";
	}

	@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {
		log.debug("index(Model uiModel, Pageable pageable)");

		Page<Client> clients = clientService.findAll(pageable);
		PageWrapper<Client> page = new PageWrapper<>(clients, "/client/list");
		uiModel.addAttribute("page", page);

		return "client/index";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		log.debug("cancel()");

		return "redirect:/client/list";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {

		Client client = clientService.findOne(id);
		uiModel.addAttribute("client", client);
		return "client/show";
	}
}
