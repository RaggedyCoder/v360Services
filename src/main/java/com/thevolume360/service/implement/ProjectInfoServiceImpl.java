package com.thevolume360.service.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.ProjectInfoDao;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.service.ProjectInfoService;
import com.thevolume360.utils.ProjectWorkIdGenerator;

@Service
@Transactional
public class ProjectInfoServiceImpl implements ProjectInfoService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectInfoServiceImpl.class);

	@Autowired
	private ProjectInfoDao projectInfoDao;

	@Override
	public ProjectInfo create(ProjectInfo projectInfo) throws Exception{
		LOGGER.debug("creating new patient with info : {}", projectInfo);
		projectInfo.setProjectWorkId(ProjectWorkIdGenerator.generate(
				projectInfo.getProjectAddress(), projectInfo.getWorkType()));
		return projectInfoDao.save(projectInfo);
	}

	@Override
	public ProjectInfo findOne(Long id) {
		// TODO Auto-generated method stub
		try {
			return projectInfoDao.findOne(id);
		} catch (Exception e) {
			System.out.println("error in service lair");
		}
		return null;
	}

	@Override
	public List<ProjectInfo> findAll() {
		// TODO Auto-generated method stub
		return projectInfoDao.findAll();
	}

	@Override
	public Page<ProjectInfo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return projectInfoDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return projectInfoDao.count();
	}

	@Override
	public void update(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub

	}

}
