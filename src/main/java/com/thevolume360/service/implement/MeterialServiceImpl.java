package com.thevolume360.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.MaterialDao;
import com.thevolume360.domain.Material;
import com.thevolume360.service.MaterialService;

@Service
public class MeterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialDao materialDao;

	@Override
	public Material create(Material material) {
		// TODO Auto-generated method stub
		return materialDao.save(material);
	}

	@Override
	public Material findOne(Long id) {
		// TODO Auto-generated method stub
		return materialDao.findOne(id);
	}

	@Override
	public List<Material> findAll() {
		// TODO Auto-generated method stub
		return materialDao.findAll();
	}

	@Override
	public Page<Material> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return materialDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return materialDao.count();
	}

	@Override
	public void update(Material material) {
		// TODO Auto-generated method stub

	}

}
