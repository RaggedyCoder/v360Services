package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.OfficeWorker;
import com.thevolume360.domain.search.OfficeWorkerSearchCmd;

public interface OfficeWorkerService {

	public OfficeWorker create(OfficeWorker officeWorker);

	public OfficeWorker findOne(Long id);

	public List<OfficeWorker> findAll();

	Page<OfficeWorker> findAll(Pageable pageable);

	public long count();

	public void update(OfficeWorker officeWorker);

	public Page<OfficeWorker> findOfficeWorkerBySearchCmd(OfficeWorkerSearchCmd officeWorkerSearchCmd,
			Pageable pageable);

}
