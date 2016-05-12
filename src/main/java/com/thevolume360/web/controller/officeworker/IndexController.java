package com.thevolume360.web.controller.officeworker;

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

import com.thevolume360.domain.OfficeWorker;
import com.thevolume360.service.OfficeWorkerService;
import com.thevolume360.utils.PageWrapper;

@Controller("officeWorkerIndexController")
@RequestMapping("/office/worker")
@Secured("ROLE_ADMIN")
public class IndexController {

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private OfficeWorkerService officeWorkerService;

	@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {
		LOG.debug("index(Model uiModel, Pageable pageable)");
		Page<OfficeWorker> officeWorkers = officeWorkerService.findAll(pageable);
		PageWrapper<OfficeWorker> page = new PageWrapper<>(officeWorkers, "/office/worker/");
		uiModel.addAttribute("page", page);

		return "office/worker/index";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		LOG.debug("show(@PathVariable(\"id\") Long id, Model uiModel)");
		LOG.info("display() id ={}", id);
		
		OfficeWorker officeWorker = officeWorkerService.findOne(id);
		uiModel.addAttribute("officeWorker", officeWorker);

		return "office/worker/show";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		LOG.debug("cancel()");
		return "redirect:/office/worker/";
	}
}
