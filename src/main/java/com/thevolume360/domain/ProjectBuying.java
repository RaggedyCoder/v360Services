package com.thevolume360.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ProjectBuying extends PersistentObject implements Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8825128945099126668L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@ManyToOne
	private ProjectInfo projectInfo;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date boughtDate;

	@OneToMany(mappedBy = "projectBuying")
	private Set<ProjectMaterial> projectMaterials = new HashSet<>();

	@NotNull
	private Long billsCost;

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

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public Date getBoughtDate() {
		return boughtDate;
	}

	public void setBoughtDate(Date boughtDate) {
		this.boughtDate = boughtDate;
	}

	public Set<ProjectMaterial> getProjectMaterials() {
		return projectMaterials;
	}

	public void setProjectMaterials(Set<ProjectMaterial> projectMaterials) {
		this.projectMaterials = projectMaterials;
	}

	public Long getBillsCost() {
		return billsCost;
	}

	public void setBillsCost(Long billsCost) {
		this.billsCost = billsCost;
	}

	@Override
	public String toString() {
		return "ProjectBuying [id=" + id + ", version=" + version
				+ ", projectInfo=" + projectInfo + ", boughtDate=" + boughtDate
				+ ", projectMaterials=" + projectMaterials + ", billsCost="
				+ billsCost + ", getCreatedDate()=" + getCreatedDate()
				+ ", getLastModifiedDate()=" + getLastModifiedDate()
				+ ", getCreatedBy()=" + getCreatedBy()
				+ ", getLastModifiedBy()=" + getLastModifiedBy() + "]";
	}

}
