package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.ProjectMaterial;

public interface ProjectMaterialService {
	
	public ProjectMaterial create(ProjectMaterial projectMaterial);

	public ProjectMaterial findOne(Long id);

	public List<ProjectMaterial> findAll();

	Page<ProjectMaterial> findAll(Pageable pageable);

	public long count();

	public void update(ProjectMaterial projectMaterial);
}
