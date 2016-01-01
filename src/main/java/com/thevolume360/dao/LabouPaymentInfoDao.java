package com.thevolume360.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.LabourWorkInfo;

@Repository
public interface LabouPaymentInfoDao extends JpaRepository<LabourWorkInfo, Long> {

	@Query("select lwi from LabourWorkInfo lwi where lwi.projectLabour.id=:projectLabourId "
			+ "and lwi.workedDate>=:activationDate")
	public List<LabourWorkInfo> findLabourWorkInfosByActivationDate(@Param("projectLabourId") Long projectLabourId,
			@Param("activationDate") Date activationDate);

	@Query("select lwi from LabourWorkInfo lwi where lwi.projectLabour.id=:projectLabourId and"
			+ " lwi.workedDate>=:activationDate and lwi.workedDate<=:lastValidDate")
	public List<LabourWorkInfo> findLabourWorkInfosByActivationDateAndLastValidDate(
			@Param("projectLabourId") Long projectLabourId, @Param("activationDate") Date activationDate,
			@Param("lastValidDate") Date lastValidDate);

}
