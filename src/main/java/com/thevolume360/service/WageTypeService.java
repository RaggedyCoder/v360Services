package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.WageType;

public interface WageTypeService {
	
	public WageType create(WageType wageType);

	public WageType findOne(Long id);

	public List<WageType> findAll();

	Page<WageType> findAll(Pageable pageable);

	public long count();

	public void update(WageType wageType);

}
