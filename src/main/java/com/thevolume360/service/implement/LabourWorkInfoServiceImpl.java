package com.thevolume360.service.implement;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.LabourWorkInfoDao;
import com.thevolume360.domain.LabourWorkInfo;
import com.thevolume360.service.LabourWorkInfoService;

@Service
@Transactional
public class LabourWorkInfoServiceImpl implements LabourWorkInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LabourWorkInfoServiceImpl.class);
	@Autowired
	private LabourWorkInfoDao labourWorkInfoDao;

	@Override
	public LabourWorkInfo create(LabourWorkInfo labourWorkInfo) {
		LOGGER.debug("creating new labour wage info with info : {}", labourWorkInfo);
		return labourWorkInfoDao.save(labourWorkInfo);
	}

	@Override
	public LabourWorkInfo findOne(Long id) {
		try {
			return labourWorkInfoDao.findOne(id);

		} catch (Exception e) {
			System.out.println("error in service lair");
		}
		return null;
	}

	@Override
	public List<LabourWorkInfo> findAll() {
		return labourWorkInfoDao.findAll();
	}

	@Override
	public Page<LabourWorkInfo> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public long count() {
		return labourWorkInfoDao.count();
	}

	@Override
	public void update(LabourWorkInfo updatedLabourWorkInfo) {
		LabourWorkInfo labourWorkInfo = labourWorkInfoDao.findOne(updatedLabourWorkInfo.getId());
		labourWorkInfo.setPaid(updatedLabourWorkInfo.getIsPaid());
		labourWorkInfo.setLabourPaymentInfo(updatedLabourWorkInfo.getLabourPaymentInfo());
		labourWorkInfo.setProjectLabour(updatedLabourWorkInfo.getProjectLabour());
		labourWorkInfo.setWorkedDate(updatedLabourWorkInfo.getWorkedDate());
		labourWorkInfo.setWorkedUnit(labourWorkInfo.getWorkedUnit());
		labourWorkInfoDao.save(labourWorkInfo);
	}

	@Override
	public List<LabourWorkInfo> findLabourWorkInfosByActivationDate(Long projectLabourId, Date activationDate) {
		// TODO Auto-generated method stub
		return labourWorkInfoDao.findLabourWorkInfosByActivationDate(projectLabourId, activationDate);
	}

	@Override
	public List<LabourWorkInfo> findLabourWorkInfosByActivationDateAndLastValidDate(Long projectLabourId,
			Date activationDate, Date lastValidDate) {
		return labourWorkInfoDao.findLabourWorkInfosByActivationDateAndLastValidDate(projectLabourId, activationDate,
				lastValidDate);
	}

}
