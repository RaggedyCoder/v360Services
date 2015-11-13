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

}
