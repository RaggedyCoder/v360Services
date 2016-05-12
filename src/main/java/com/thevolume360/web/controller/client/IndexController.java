package com.thevolume360.web.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.domain.Client;
import com.thevolume360.service.ClientService;
import com.thevolume360.utils.PageWrapper;

@Controller("clientIndexController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/client")
public class IndexController {

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {
		LOG.debug("index(Model uiModel, Pageable pageable)");

		Page<Client> clients = clientService.findAll(pageable);
		PageWrapper<Client> page = new PageWrapper<>(clients, "/client/list");
		uiModel.addAttribute("page", page);

		return "client/index";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		LOG.debug("cancel()");
		return "redirect:/client/list";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {
		LOG.debug("show(@PathVariable Long id, Model uiModel)");
		LOG.info("display() id ={}", id);
		Client client = clientService.findOne(id);
		uiModel.addAttribute("client", client);
		return "client/show";
	}

}
