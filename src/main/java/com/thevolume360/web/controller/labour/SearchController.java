package com.thevolume360.web.controller.labour;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.thevolume360.domain.Labour;
import com.thevolume360.domain.enums.Gender;
import com.thevolume360.domain.search.LabourSearchCmd;
import com.thevolume360.service.LabourService;
import com.thevolume360.utils.PageWrapper;
import com.thevolume360.web.editor.GenderEditor;

@Controller("labourSearchController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/labour")
public class SearchController {

	private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private LabourService labourService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Gender.class, new GenderEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(Model uiModel) {
		LOG.debug("search()");

		uiModel.addAttribute("labourSearchCmd", new LabourSearchCmd());

		return "labour/search";
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String searchResult(@ModelAttribute("labourSearchCmd") LabourSearchCmd labourSearchCmd, Pageable pageable,
			Model uiModel, HttpServletRequest request) {
		LOG.info("display() labourSearchCmd ={}", labourSearchCmd);

		if (isEmpty(labourSearchCmd.getFullName().getFirstName())
				&& isEmpty(labourSearchCmd.getFullName().getLastName()) && isEmpty(labourSearchCmd.getContactNumber())
				&& labourSearchCmd.getGender() != null) {

			uiModel.addAttribute("labourSearchCmd", labourSearchCmd);
			uiModel.addAttribute("error", "Please enter name or Phone Number or gender");

			return "labour/search";
		}
		Page<Labour> labours = labourService.findLabourBySearchCmd(labourSearchCmd, pageable);
		if (labours == null || labours.getTotalElements() == 0) {
			uiModel.addAttribute("labourSearchCmd", labourSearchCmd);
			uiModel.addAttribute("notFound", "The labour Information you are looking for, doesn't exist!");

			return "labour/search";
		}
		PageWrapper<Labour> pageWrapper = new PageWrapper<>(labours, "/labour/display?" + request.getQueryString());
		uiModel.addAttribute("page", pageWrapper);

		if (labours.getTotalElements() == 0) {

			uiModel.addAttribute("labourSearchCmd", labourSearchCmd);
			uiModel.addAttribute("notFound", "The labour Information you are looking for, doesn't exist!");

			return "labour/search";
		}
		return "labour/index";
	}

	private boolean isEmpty(String string) {
		return string.length() != 0;
	}

}
