package com.thevolume360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.LabourDao;
import com.thevolume360.domain.Labour;
import com.thevolume360.domain.ProjectInfo;

@Service
@Transactional
public class LabourServiceImpl implements LabourService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LabourServiceImpl.class);

	@Autowired
	private LabourDao labourDao;

	@Override
	public Labour create(Labour labour) {
		LOGGER.debug("creating new patient with info : {}", labour);
		return labourDao.save(labour);
	}

	@Override
	public Labour findOne(Long id) {
		return labourDao.findOne(id);
	}

	@Override
	public List<Labour> findAll() {
		return labourDao.findAll();
	}

	@Override
	public Page<Labour> findAll(Pageable pageable) {
		return labourDao.findAll(pageable);
	}

	@Override
	public long count() {
		return labourDao.count();
	}

	@Override
	public void update(Labour labour) {
		LOGGER.debug("Updating labour with info : {}", labour);
	}

	@Override
	public List<Labour> getLaboursNotInThisProject(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub
		return labourDao.findLabourNotInProjectByProjectInfoId(projectInfo
				.getId());
	}
}
