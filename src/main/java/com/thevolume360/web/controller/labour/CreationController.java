package com.thevolume360.web.controller.labour;

import static com.thevolume360.utils.Constant.DEBUG_METHOD_ENTER;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.Labour;
import com.thevolume360.domain.enums.Gender;
import com.thevolume360.service.LabourService;
import com.thevolume360.web.editor.GenderEditor;

@Controller("labourCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour")
public class CreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CreationController.class);

	private static final String TAG = CreationController.class.getSimpleName();

	@Autowired
	private LabourService labourService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Gender.class, new GenderEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Labour labour, Model uiModel) {
		LOG.debug(DEBUG_METHOD_ENTER, "create(Labour labour, Model uiModel)", TAG);
		return "labour/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Valid Labour labour, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {

			return "labour/create";
		}
		labourService.create(labour);
		redirectAttributes.addFlashAttribute("message", String.format("Labour successfully created"));
		return "redirect:/labour/show/" + labour.getId().toString();
	}
}
