package com.thevolume360.web.controller.project;

import static com.thevolume360.utils.Constant.DEBUG_METHOD_ENTER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.utils.PageWrapper;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project")
public class IndexController {

	private static final String TAG = IndexController.class.getSimpleName();

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ProjectInfoService projectInfoService;

	@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {
		LOG.debug(DEBUG_METHOD_ENTER, "index(Model uiModel, Pageable pageable)", TAG);
		uiModel.addAttribute("page", new PageWrapper<>(projectInfoService.findAll(pageable), "/project/list"));
		return "project/index";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {
		LOG.debug(DEBUG_METHOD_ENTER, "show(@PathVariable Long id, Model uiModel)", TAG);
		uiModel.addAttribute("projectInfo", projectInfoService.findOne(id));
		uiModel.addAttribute("labourWageCost", labourWageCost());
		return "project/show";
	}

	private Integer labourWageCost() {
		return null;
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		LOG.debug(DEBUG_METHOD_ENTER, "cancel()", TAG);
		return "redirect:/project/list";
	}

}
