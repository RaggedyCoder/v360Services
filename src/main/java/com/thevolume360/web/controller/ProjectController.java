package com.thevolume360.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.Client;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.domain.enums.ClientType;
import com.thevolume360.domain.enums.WorkType;
import com.thevolume360.service.ClientService;
import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.utils.PageWrapper;
import com.thevolume360.web.editor.ClientTypeEditor;
import com.thevolume360.web.editor.WorkTypeEditor;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project")
public class ProjectController {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectController.class);

	@Autowired
	private ProjectInfoService projectInfoService;

	@Autowired
	private ClientService clientService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(WorkType.class, new WorkTypeEditor());
		binder.registerCustomEditor(ClientType.class, new ClientTypeEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
	public String index(Model uiModel, Pageable pageable) {

		Page<ProjectInfo> projectInfos = projectInfoService.findAll(pageable);
		PageWrapper<ProjectInfo> page = new PageWrapper<>(projectInfos,
				"/project/list");
		uiModel.addAttribute("page", page);
		return "projects/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ProjectInfo projectInfo, Model uiModel) {
		try {
			List<Client> clients = clientService.findAll();
			uiModel.addAttribute("clients", clients);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "because-"
					+ clientService.count());
		}

		System.out.println("ok get");
		return "projects/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Valid ProjectInfo projectInfo, BindingResult result,
			Model uiModel, RedirectAttributes redirectAttributes) {
		validateProjectInfo(projectInfo, result);
		System.out.println(projectInfo);
		if (projectInfo.getClient().getId() != null) {
			Client client = clientService.findOne(projectInfo.getClient()
					.getId());
			// System.err.println(client);
			projectInfo.setClient(client);
			 System.err.println(projectInfo);
			if (result.hasErrors()) {
				for(FieldError fieldError:result.getFieldErrors()){
					System.out.println(fieldError);
				}
				List<Client> clients = clientService.findAll();
				uiModel.addAttribute("clients", clients);
				return "projects/create";
			}
		} else {
			System.err.println(projectInfo);
			if (!projectInfo.getClient().getName().isEmpty()
					&& projectInfo.getClient().getClientType() != null) {
				System.err.println("2" + projectInfo);
				clientService.create(projectInfo.getClient());
			}
			if (result.hasErrors()) {
				System.err.println("3" + projectInfo);
				List<Client> clients = clientService.findAll();
				uiModel.addAttribute("clients", clients);
				return "projects/create";
			}
		}
		try {
			System.out.println("doing");
			projectInfoService.create(projectInfo);
			System.out.println("done");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		redirectAttributes.addFlashAttribute("message",
				String.format("Project successfully created"));
		return "redirect:/project/list";
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model uiModel) {
		System.out.println("ok");
		ProjectInfo projectInfo = null;
		try {
			projectInfo = projectInfoService.findOne(id);
		} catch (Exception e) {
			System.out.println("error.");
			System.out.println(e.getMessage());
		}
		System.out.println("data got");
		System.err.println(projectInfo.getProjectLabours().size());
		uiModel.addAttribute("projectInfo", projectInfo);
		return "projects/show";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		log.debug("cancel()");
		return "redirect:/project/list";
	}

	private void validateProjectInfo(ProjectInfo projectInfo,
			BindingResult result) {
	}
}
