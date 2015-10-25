package com.thevolume360.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.ProjectBuyingDao;
import com.thevolume360.domain.ProjectBuying;
import com.thevolume360.service.ProjectBuyingService;

@Service
public class ProjectBuyingServiceImpl implements ProjectBuyingService {

	@Autowired
	private ProjectBuyingDao projectBuyingDao;

	@Override
	public ProjectBuying create(ProjectBuying projectBuying) {
		return projectBuyingDao.save(projectBuying);
	}

	@Override
	public ProjectBuying findOne(Long id) {
		// TODO Auto-generated method stub
		return projectBuyingDao.findOne(id);
	}

	@Override
	public List<ProjectBuying> findAll() {
		// TODO Auto-generated method stub
		return projectBuyingDao.findAll();
	}

	@Override
	public Page<ProjectBuying> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return projectBuyingDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return projectBuyingDao.count();
	}

	@Override
	public void update(ProjectBuying projectBuying) {
		// TODO Auto-generated method stub

	}

}
