package com.thevolume360.service.implement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.ProjectMaterialDao;
import com.thevolume360.domain.ProjectMaterial;
import com.thevolume360.service.ProjectMaterialService;

@Service
public class ProjectMaterialServiceImpl implements ProjectMaterialService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectMaterialServiceImpl.class);
	@Autowired
	private ProjectMaterialDao projectMaterialDao;

	@Override
	public ProjectMaterial create(ProjectMaterial projectMaterial) {
		LOGGER.debug("creating new project labour with info : {}", projectMaterial);
		System.out.println("creating");
		try {
			return projectMaterialDao.save(projectMaterial);
		} catch (Exception e) {
			System.out.println("error");
			System.out.println(e.getClass().getSimpleName());
		}
		System.out.println("error");
		return null;
	}

	@Override
	public ProjectMaterial findOne(Long id) {
		try {
			return projectMaterialDao.findOne(id);

		} catch (Exception e) {
			System.out.println("error in service lair");
		}
		return null;
	}

	@Override
	public List<ProjectMaterial> findAll() {
		return projectMaterialDao.findAll();
	}

	@Override
	public Page<ProjectMaterial> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return projectMaterialDao.count();
	}

	@Override
	public void update(ProjectMaterial ProjectMaterial) {
		// TODO Auto-generated method stub

	}

}
