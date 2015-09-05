package com.thevolume360.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.OfficeWorkerDao;
import com.thevolume360.domain.OfficeWorker;
import com.thevolume360.service.OfficeWorkerService;

@Service
public class OfiiceWorkerServiceImpl implements OfficeWorkerService {

	@Autowired
	private OfficeWorkerDao officeWorkerDao;

	@Override
	public OfficeWorker create(OfficeWorker officeWorker) {
		// TODO Auto-generated method stub
		return officeWorkerDao.save(officeWorker);
	}

	@Override
	public OfficeWorker findOne(Long id) {
		// TODO Auto-generated method stub
		return officeWorkerDao.findOne(id);
	}

	@Override
	public List<OfficeWorker> findAll() {
		// TODO Auto-generated method stub
		return officeWorkerDao.findAll();
	}

	@Override
	public Page<OfficeWorker> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return officeWorkerDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return officeWorkerDao.count();
	}

	@Override
	public void update(OfficeWorker officeWorker) {
	}

}
