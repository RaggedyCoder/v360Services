package com.thevolume360.web.controller.officeworker;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.OfficeWorker;
import com.thevolume360.domain.enums.Designation;
import com.thevolume360.service.OfficeWorkerService;
import com.thevolume360.web.editor.DesignationEditor;

@Controller("officeWorkerCreationController")
@RequestMapping("/office/worker")
@Secured("ROLE_ADMIN")
public class CreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CreationController.class);

	@Autowired
	private OfficeWorkerService officeWorkerService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(Designation.class, new DesignationEditor());
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(OfficeWorker officeWorker) {
		LOG.debug("create(OfficeWorker officeWorker)");
		LOG.info("display officeWorker ={}", officeWorker);
		return "office/worker/create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String save(@Valid OfficeWorker officeWorker, BindingResult result, RedirectAttributes redirectAttrs) {

		LOG.debug("create(OfficeWorker officeWorker)");
		LOG.info("display officeWorker ={}", officeWorker);
		LOG.info("display errors ={}", result.getAllErrors());

		if (result.hasErrors()) {
			return "office/worker/create";
		}

		officeWorkerService.create(officeWorker);

		redirectAttrs.addFlashAttribute("message", "Successfully office worker created");

		return "redirect:/office/worker/show/" + officeWorker.getId().toString();
	}
}
