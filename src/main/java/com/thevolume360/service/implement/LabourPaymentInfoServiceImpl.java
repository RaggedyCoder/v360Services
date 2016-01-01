package com.thevolume360.service.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.LabourPaymentInfoDao;
import com.thevolume360.domain.LabourPaymentInfo;
import com.thevolume360.service.LabourPaymentInfoService;

@Service
@Transactional
public class LabourPaymentInfoServiceImpl implements LabourPaymentInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LabourPaymentInfoServiceImpl.class);
	@Autowired
	private LabourPaymentInfoDao labourPaymentInfoDao;

	@Override
	public LabourPaymentInfo create(LabourPaymentInfo labourPaymentInfo) {
		LOGGER.debug("create(LabourPaymentInfo labourPaymentInfo)");
		LOGGER.info("labourPaymentInfo : ={}", labourPaymentInfo);
		return labourPaymentInfoDao.save(labourPaymentInfo);
	}

	@Override
	public LabourPaymentInfo findOne(Long id) {
		LOGGER.debug("findOne(Long id)");
		LOGGER.info("labourPaymentInfo->id : ={}", id);
		try {
			return labourPaymentInfoDao.findOne(id);
		} catch (Exception e) {
			LOGGER.error("Exception ={}, Message ={}", e.getClass().getSimpleName(),e.getMessage());
		}
		return null;
	}

	@Override
	public List<LabourPaymentInfo> findAll() {
		LOGGER.debug("findAll()");
		return labourPaymentInfoDao.findAll();
	}

	@Override
	public Page<LabourPaymentInfo> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public long count() {
		return labourPaymentInfoDao.count();
	}

	@Override
	public void update(LabourPaymentInfo labourPaymentInfo) {
	}

}
