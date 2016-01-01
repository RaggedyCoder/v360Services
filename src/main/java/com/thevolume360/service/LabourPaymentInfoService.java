package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.LabourPaymentInfo;

public interface LabourPaymentInfoService {

	public LabourPaymentInfo create(LabourPaymentInfo labourPaymentInfo);

	public LabourPaymentInfo findOne(Long id);

	public List<LabourPaymentInfo> findAll();

	Page<LabourPaymentInfo> findAll(Pageable pageable);

	public long count();

	public void update(LabourPaymentInfo labourPaymentInfo);

}
