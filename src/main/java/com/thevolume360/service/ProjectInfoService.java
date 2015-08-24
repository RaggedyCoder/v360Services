package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.ProjectInfo;

public interface ProjectInfoService {

	public ProjectInfo create(ProjectInfo projectInfo) throws Exception;

	public ProjectInfo findOne(Long id);

	public List<ProjectInfo> findAll();

	Page<ProjectInfo> findAll(Pageable pageable);

	public long count();

	public void update(ProjectInfo projectInfo);

}
