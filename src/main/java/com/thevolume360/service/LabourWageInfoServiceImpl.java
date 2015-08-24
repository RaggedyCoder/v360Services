package com.thevolume360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.LabourWageInfoDao;
import com.thevolume360.domain.LabourWageInfo;

@Service
@Transactional
public class LabourWageInfoServiceImpl implements LabourWageInfoService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LabourWageInfoServiceImpl.class);
	@Autowired
	private LabourWageInfoDao labourWageInfoDao;

	@Override
	public LabourWageInfo create(LabourWageInfo labourWageInfo) {
		LOGGER.debug("creating new labour wage info with info : {}",
				labourWageInfo);
		return labourWageInfoDao.save(labourWageInfo);
	}

	@Override
	public LabourWageInfo findOne(Long id) {
		try {
			return labourWageInfoDao.findOne(id);

		} catch (Exception e) {
			System.out.println("error in service lair");
		}
		return null;
	}

	@Override
	public List<LabourWageInfo> findAll() {
		return labourWageInfoDao.findAll();
	}

	@Override
	public Page<LabourWageInfo> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public long count() {
		return labourWageInfoDao.count();
	}

	@Override
	public void update(LabourWageInfo LabourWageInfo) {
	}

}
