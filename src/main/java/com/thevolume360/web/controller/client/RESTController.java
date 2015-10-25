package com.thevolume360.web.controller.client;

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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ClientType.class, new ClientTypeEditor());
	}

	@Autowired
	private ClientService clientService;
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public Client create(Client client) {
		return clientService.create(client);
	}

}
