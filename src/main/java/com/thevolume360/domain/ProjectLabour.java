package com.thevolume360.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

@Entity
public class ProjectLabour extends PersistentObject implements Auditable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7956003221906466712L;

	static final Logger LOG = LoggerFactory.getLogger(ProjectLabour.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	private Long id;

	@Version
	private Long version;
	@NotNull
	@ManyToOne
	@Expose
	private Labour labour;

	@NotNull
	@ManyToOne
	@Expose
	private ProjectInfo projectInfo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date projectJoinDate = new Date(System.currentTimeMillis());

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date projectEndDate = null;

	@OneToMany(mappedBy = "projectLabour", fetch = FetchType.LAZY)
	@Expose
	private Set<LabourWageInfo> labourWageInfos = new HashSet<>();

	@OneToMany(mappedBy = "projectLabour", fetch = FetchType.LAZY)
	private Set<LabourWorkInfo> labourWorkInfos = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Labour getLabour() {
		return labour;
	}

	public void setLabour(Labour labour) {
		this.labour = labour;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public Date getProjectJoinDate() {
		return projectJoinDate;
	}

	public void setProjectJoinDate(Date projectJoinDate) {
		this.projectJoinDate = projectJoinDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public Set<LabourWageInfo> getLabourWageInfos() {
		return labourWageInfos;
	}

	public void setLabourWageInfos(Set<LabourWageInfo> labourWageInfos) {
		this.labourWageInfos = labourWageInfos;
	}

	public Set<LabourWorkInfo> getLabourWorkInfos() {
		return labourWorkInfos;
	}

	public void setLabourWorkInfos(Set<LabourWorkInfo> labourWorkInfos) {
		this.labourWorkInfos = labourWorkInfos;
	}

}
