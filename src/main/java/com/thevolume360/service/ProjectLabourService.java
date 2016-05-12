package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.ProjectLabour;

public interface ProjectLabourService {

	public ProjectLabour create(ProjectLabour ProjectLabour);

	public ProjectLabour findOne(Long id);

	public List<ProjectLabour> findAll();

	Page<ProjectLabour> findAll(Pageable pageable);

	public long count();

	public void update(ProjectLabour ProjectLabour);

}
