package com.thevolume360.web.controller.labour;

import static com.thevolume360.utils.Constant.DEBUG_METHOD_ENTER;

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

import com.thevolume360.domain.Labour;
import com.thevolume360.service.LabourService;
import com.thevolume360.utils.PageWrapper;

@Controller("labourIndexController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour")
public class IndexController {

	@Autowired
	private LabourService labourService;

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

	private static final String TAG = IndexController.class.getSimpleName();

	@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {
		LOG.debug(DEBUG_METHOD_ENTER, "index(Model uiModel, Pageable pageable)", TAG);
		Page<Labour> labours = labourService.findAll(pageable);
		PageWrapper<Labour> page = new PageWrapper<>(labours, "/labour/list");
		uiModel.addAttribute("page", page);

		return "labour/index";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {

		Labour labour = labourService.findOne(id);
		uiModel.addAttribute("labour", labour);
		System.out.println(labour);
		return "labour/show";
	}

	@RequestMapping(value = "details/{id}", method = RequestMethod.GET)
	public String details(@PathVariable("id") Long id, Model uiModel) {

		uiModel.addAttribute("labour", labourService.findOne(id));

		return "labour/details";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		LOG.debug("cancel()");

		return "redirect:/labour/list";
	}
}
