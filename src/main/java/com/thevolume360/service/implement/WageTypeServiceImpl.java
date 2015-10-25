package com.thevolume360.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.WageTypeDao;
import com.thevolume360.domain.WageType;
import com.thevolume360.service.WageTypeService;

@Service
public class WageTypeServiceImpl implements WageTypeService {

	@Autowired
	private WageTypeDao wageTypeDao;

	@Override
	public WageType create(WageType wageType) {
		// TODO Auto-generated method stub
		return wageTypeDao.save(wageType);
	}

	@Override
	public WageType findOne(Long id) {
		// TODO Auto-generated method stub
		return wageTypeDao.findOne(id);
	}

	@Override
	public List<WageType> findAll() {
		// TODO Auto-generated method stub
		return wageTypeDao.findAll();
	}

	@Override
	public Page<WageType> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return wageTypeDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return wageTypeDao.count();
	}

	@Override
	public void update(WageType wageType) {
		// TODO Auto-generated method stub

	}

}
