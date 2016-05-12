package com.thevolume360.web.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thevolume360.domain.Client;
import com.thevolume360.domain.enums.ClientType;
import com.thevolume360.service.ClientService;
import com.thevolume360.web.editor.ClientTypeEditor;

@RestController(value = "clientRESTController")
@RequestMapping("/restapi/client")
public class RESTController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RESTController.class);
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(ClientType.class, new ClientTypeEditor());
	}

	@Autowired
	private ClientService clientService;
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public Client create(Client client) {
		LOG.debug("create(Client client)");
		LOG.info("display() client ={}", client);	
		return clientService.create(client);
	}

}
