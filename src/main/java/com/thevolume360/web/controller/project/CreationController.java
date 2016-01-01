package com.thevolume360.web.controller.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thevolume360.domain.Attachment;
import com.thevolume360.domain.Client;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.domain.enums.ClientType;
import com.thevolume360.domain.enums.WorkType;
import com.thevolume360.service.AttachmentService;
import com.thevolume360.service.ClientService;
import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.utils.FileUtils;
import com.thevolume360.web.editor.ClientTypeEditor;
import com.thevolume360.web.editor.WorkTypeEditor;

@Controller(value = "projectCreationController")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/project")
public class CreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CreationController.class);

	@Autowired
	private ClientService clientService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@Autowired
	private AttachmentService attachmentService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(WorkType.class, new WorkTypeEditor());
		binder.registerCustomEditor(ClientType.class, new ClientTypeEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ProjectInfo projectInfo, Model uiModel) {
		try {
			uiModel.addAttribute("clients", clientService.findAll());
		} catch (Exception e) {
			LOG.error("%s exception was caught due to % reason.", e.getClass().getName(), e.getMessage());
		}
		return "project/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@RequestParam("file") MultipartFile[] file, @Valid ProjectInfo projectInfo, BindingResult result,
			Model uiModel, RedirectAttributes redirectAttributes) {
		if (projectInfo.getClient().getId() != null) {
			System.err.println(projectInfo);
			if (result.hasErrors()) {
				for (FieldError fieldError : result.getFieldErrors()) {
					System.out.println(fieldError);
				}
				List<Client> clients = clientService.findAll();
				uiModel.addAttribute("clients", clients);
				return "project/create";
			}
		} else {
			System.out.println(file.length);
			System.err.println(projectInfo);
			if (!projectInfo.getClient().getName().isEmpty() && projectInfo.getClient().getClientType() != null) {
				clientService.create(projectInfo.getClient());
			}
			if (result.hasErrors()) {
				return "project/create";
			}
		}
		try {
			projectInfo = projectInfoService.create(projectInfo);
			redirectAttributes.addFlashAttribute("message",
					String.format("Project %s successfully created", projectInfo.getProjectName()));
		} catch (Exception e) {
			LOG.error("={} exception was caught due to ={} reason.", e.getClass().getName(), e.getMessage());
			redirectAttributes.addFlashAttribute("message",
					String.format("Project %s wasn't created successfully.", projectInfo.getProjectName()));
		}
		Set<Attachment> attachments = createAttachments(file, projectInfo);
		for (Attachment attachment : attachments) {
			attachmentService.create(attachment);
		}
		return "redirect:/project/list";
	}

	private Set<Attachment> createAttachments(MultipartFile[] file, ProjectInfo projectInfo) {
		Set<Attachment> attachments = new HashSet<>();
		for (int i = 0; i < file.length; i++) {
			if (FileUtils.isValidFile(file[i], FileUtils.getFileType())) {
				Attachment attachment = new Attachment();
				attachment.setFileName(FileUtils.getFilteredFileName(file[i].getOriginalFilename(), ProjectInfo.class));
				attachment.setMimeType(FileUtils.getExtensionInLowerCase(file[i].getOriginalFilename()));
				attachment.setImageCategory(
						FileUtils.getContentType(FileUtils.getExtensionInLowerCase(file[i].getOriginalFilename())));
				attachment.setPath("/");
				attachment.setProjectInfo(projectInfo);
				attachments.add(attachment);
			}
		}
		return attachments;
	}
}
