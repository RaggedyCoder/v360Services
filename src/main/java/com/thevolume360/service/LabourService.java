package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.Labour;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.domain.search.LabourSearchCmd;

public interface LabourService {

	public Labour create(Labour labour);

	public Labour findOne(Long id);

	public List<Labour> findAll();

	Page<Labour> findAll(Pageable pageable);

	public long count();

	public void update(Labour labour);

	public List<Labour> getLaboursNotInThisProject(ProjectInfo projectInfo);

	public Page<Labour> findLabourBySearchCmd(LabourSearchCmd labourSearchCmd, Pageable pageable);
}
