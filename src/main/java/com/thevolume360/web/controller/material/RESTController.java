package com.thevolume360.web.controller.material;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thevolume360.domain.Material;
import com.thevolume360.domain.enums.MaterialType;
import com.thevolume360.domain.enums.WageCategory;
import com.thevolume360.service.MaterialService;
import com.thevolume360.web.editor.MaterialTypeEditor;
import com.thevolume360.web.editor.WageCategoryEditor;

@RestController(value = "materialRESTController")
@RequestMapping("/restapi/material")
public class RESTController {

	private static final Logger LOG = LoggerFactory.getLogger(RESTController.class);

	@Autowired
	private MaterialService materialService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOG.debug("initBinder(WebDataBinder binder)");
		binder.registerCustomEditor(MaterialType.class, new MaterialTypeEditor());
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public Material create(Material material) {
		LOG.debug("create(Material material)");
		LOG.info("display() material ={}", material);

		return materialService.create(material);
	}

}