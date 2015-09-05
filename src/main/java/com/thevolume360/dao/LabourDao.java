package com.thevolume360.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.Labour;

@Repository
public interface LabourDao extends JpaRepository<Labour, Long>, JpaSpecificationExecutor<Labour> {

	@Query("select l from Labour l left join l.projectLabours pl on pl.projectInfo.id=:projectInfoid where pl.projectInfo.id is null")
	public List<Labour> findLabourNotInProjectByProjectInfoId(@Param("projectInfoid") Long projectInfoId);

	/*
	 * @Query(
	 * "select l from Labour l where l.fullName.firstName like %:firstName% or l.fullName.lastName like %:lastName% or l.contactNumber like %:contactNumber%"
	 * ) public Page<Labour> searchLabour(@Param("firstName") String
	 * firstName, @Param("lastName") String lastName,
	 * 
	 * @Param("contactNumber") String contactNumber, Pageable pageable);
	 */}
