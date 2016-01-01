package com.thevolume360.web.controller.officeworker;

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

import com.thevolume360.domain.OfficeWorker;
import com.thevolume360.domain.enums.Designation;
import com.thevolume360.domain.search.OfficeWorkerSearchCmd;
import com.thevolume360.service.OfficeWorkerService;
import com.thevolume360.utils.PageWrapper;
import com.thevolume360.web.editor.ClientTypeEditor;

@Controller("officeWorkerSearchController")
@RequestMapping("/office/worker")
@Secured("ROLE_ADMIN")
public class SearchController {
	private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private OfficeWorkerService officeWorkerService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(Designation.class, new ClientTypeEditor());
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(Model uiModel) {
		LOG.debug("search(Model uiModel)");

		uiModel.addAttribute("officeWorkerSearchCmd", new OfficeWorkerSearchCmd());

		return "office/worker/search";
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String searchResult(@ModelAttribute("oficeWorkerSearchCmd") OfficeWorkerSearchCmd oficeWorkerSearchCmd,
			Pageable pageable, Model uiModel, HttpServletRequest request) {
		LOG.info("display() oficeWorkerSearchCmd ={}", oficeWorkerSearchCmd);

		if (isEmpty(oficeWorkerSearchCmd.getFullName().getFirstName())
				&& isEmpty(oficeWorkerSearchCmd.getFullName().getLastName())
				&& isEmpty(oficeWorkerSearchCmd.getContactNumber()) && oficeWorkerSearchCmd.getDesignation() != null) {
			uiModel.addAttribute("officeWorkerSearchCmd", oficeWorkerSearchCmd);
			uiModel.addAttribute("error", "Please enter name or Phone Number or designation");
			return "office/worker/search";
		}
		Page<OfficeWorker> officeWorkers = officeWorkerService.findOfficeWorkerBySearchCmd(oficeWorkerSearchCmd,
				pageable);
		if (officeWorkers == null || officeWorkers.getTotalElements() == 0) {
			uiModel.addAttribute("officeWorkerSearchCmd", oficeWorkerSearchCmd);
			uiModel.addAttribute("notFound", "The Office Worker Information you are looking for, doesn't exist!");

			return "office/worker/search";
		}
		PageWrapper<OfficeWorker> pageWrapper = new PageWrapper<>(officeWorkers,
				"/office/worker/display?" + request.getQueryString());
		uiModel.addAttribute("page", pageWrapper);

		if (officeWorkers.getTotalElements() == 0) {

			uiModel.addAttribute("officeWorkerSearchCmd", oficeWorkerSearchCmd);
			uiModel.addAttribute("notFound", "The Office Worker Information you are looking for, doesn't exist!");

			return "office/worker/search";
		}
		return "office/worker/index";
	}

	private boolean isEmpty(String string) {
		return string.length() != 0;
	}
}
