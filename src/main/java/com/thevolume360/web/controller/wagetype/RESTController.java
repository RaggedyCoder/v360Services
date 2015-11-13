package com.thevolume360.web.controller.wagetype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thevolume360.domain.WageType;
import com.thevolume360.domain.enums.WageCategory;
import com.thevolume360.service.WageTypeService;
import com.thevolume360.web.editor.WageCategoryEditor;

@RestController(value = "wageTypeRESTController")
@RequestMapping("/restapi/wagetype")
public class RESTController {

	private static final Logger LOG = LoggerFactory.getLogger(RESTController.class);
	
	@Autowired
	private WageTypeService wageTypeService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(WageCategory.class, new WageCategoryEditor());
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public WageType create(WageType wageType) {
		LOG.debug("create(WageType wageType)");
		LOG.info("display() wageType ={}", wageType);	

		return wageTypeService.create(wageType);
	}

}