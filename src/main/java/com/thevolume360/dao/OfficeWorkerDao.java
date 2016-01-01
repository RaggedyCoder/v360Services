package com.thevolume360.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.OfficeWorker;

@Repository
public interface OfficeWorkerDao extends JpaRepository<OfficeWorker, Long>, JpaSpecificationExecutor<OfficeWorker> {

}
