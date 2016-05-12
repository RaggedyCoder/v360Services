package com.thevolume360.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.LabourWorkInfo;

public interface LabourWorkInfoService {

	public LabourWorkInfo create(LabourWorkInfo labourWorkInfo);

	public LabourWorkInfo findOne(Long id);

	public List<LabourWorkInfo> findAll();

	Page<LabourWorkInfo> findAll(Pageable pageable);

	public long count();

	public void update(LabourWorkInfo updatedLabourWorkInfo);

	public List<LabourWorkInfo> findLabourWorkInfosByActivationDate(Long projectLabourId, Date activationDate);

	public List<LabourWorkInfo> findLabourWorkInfosByActivationDateAndLastValidDate(Long projectLabourId,
			Date activationDate, Date lastValidDate);

}
