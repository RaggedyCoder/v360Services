package com.thevolume360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.ProjectLabourDao;
import com.thevolume360.domain.ProjectLabour;

@Service
@Transactional
public class ProjectLabourServiceImpl implements ProjectLabourService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectLabourServiceImpl.class);
	@Autowired
	private ProjectLabourDao projectLabourDao;

	@Override
	public ProjectLabour create(ProjectLabour projectLabour) {
		LOGGER.debug("creating new project labour with info : {}",
				projectLabour);
		System.out.println("creating");
		try {
			return projectLabourDao.save(projectLabour);
		} catch (Exception e) {
			System.out.println("error");
			System.out.println(e.getClass().getSimpleName());
		}
		System.out.println("error");
		return null;
	}

	@Override
	public ProjectLabour findOne(Long id) {
		try {
			return projectLabourDao.findOne(id);

		} catch (Exception e) {
			System.out.println("error in service lair");
		}
		return null;
	}

	@Override
	public List<ProjectLabour> findAll() {
		return projectLabourDao.findAll();
	}

	@Override
	public Page<ProjectLabour> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(ProjectLabour ProjectLabour) {
		// TODO Auto-generated method stub

	}

}
