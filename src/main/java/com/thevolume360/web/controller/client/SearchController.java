package com.thevolume360.web.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.domain.Client;
import com.thevolume360.domain.enums.ClientType;
import com.thevolume360.domain.search.ClientSearchCmd;
import com.thevolume360.service.ClientService;
import com.thevolume360.utils.PageWrapper;
import com.thevolume360.web.editor.ClientTypeEditor;

@Controller("clientSearchController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/client")
public class SearchController {

	private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private ClientService clientService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(ClientType.class, new ClientTypeEditor());
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(Model uiModel) {
		LOG.debug("search(Model uiModel)");

		uiModel.addAttribute("clientSearchCmd", new ClientSearchCmd());

		return "client/search";
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String searchResult(@ModelAttribute("clientSearchCmd") ClientSearchCmd clientSearchCmd, Pageable pageable,
			Model uiModel, HttpServletRequest request) {
		LOG.debug("searchResult(@ModelAttribute(\"clientSearchCmd\") "
				+ "ClientSearchCmd clientSearchCmd, Pageable pageable,Model uiModel, " + "HttpServletRequest request)");
		LOG.info("display() clientSearchCmd ={}", clientSearchCmd);

		if (isEmptyQuery(clientSearchCmd)) {
			uiModel.addAttribute("clientSearchCmd", clientSearchCmd);
			uiModel.addAttribute("error", "Please enter name or select type of the client");
			return "client/search";
		}
		Page<Client> clients = clientService.findClientBySearchCmd(clientSearchCmd, pageable);

		if (isNull(clients) || clients.getTotalElements() == 0) {
			uiModel.addAttribute("clientSearchCmd", clientSearchCmd);
			uiModel.addAttribute("notFound", "The Client Information you are looking for, doesn't exist!");
			return "client/search";
		}

		PageWrapper<Client> pageWrapper = new PageWrapper<>(clients, "/client/display?" + request.getQueryString());
		uiModel.addAttribute("page", pageWrapper);

		if (clients.getTotalElements() == 0) {
			uiModel.addAttribute("clientSearchCmd", clientSearchCmd);
			uiModel.addAttribute("notFound", "The Client Information you are looking for, doesn't exist!");
			return "client/search";
		}
		return "client/index";
	}

	private boolean isEmptyQuery(ClientSearchCmd clientSearchCmd) {
		return isEmpty(clientSearchCmd.getName().trim()) && isNull(clientSearchCmd.getClientType());
	}

	private boolean isEmpty(String string) {
		return string.length() != 0;
	}

	private boolean isNull(Object object) {
		return object == null;
	}

}
