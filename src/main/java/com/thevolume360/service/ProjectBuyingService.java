package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.ProjectBuying;

public interface ProjectBuyingService {
	
	public ProjectBuying create(ProjectBuying projectBuying);

	public ProjectBuying findOne(Long id);

	public List<ProjectBuying> findAll();

	Page<ProjectBuying> findAll(Pageable pageable);

	public long count();

	public void update(ProjectBuying projectBuying);
}
