package com.thevolume360.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.LabourWageInfo;

@Repository
public interface LabourWageInfoDao extends JpaRepository<LabourWageInfo, Long> {
	@Query("select lwi from LabourWageInfo lwi where lwi.activationDate<=:workedDate and (lwi.lastValidDate is null or lwi.lastValidDate>=:workedDate)")
	public LabourWageInfo findLabourWageInfoByWorkedDate(@Param("workedDate") Date workedDate);
}
