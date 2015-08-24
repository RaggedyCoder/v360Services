package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.LabourWageInfo;

public interface LabourWageInfoService {

	public LabourWageInfo create(LabourWageInfo labourWageInfo);

	public LabourWageInfo findOne(Long id);

	public List<LabourWageInfo> findAll();

	Page<LabourWageInfo> findAll(Pageable pageable);

	public long count();

	public void update(LabourWageInfo labourWageInfo);
}
