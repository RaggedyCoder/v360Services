package com.thevolume360.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class LabourWageInfo extends PersistentObject implements Auditable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -188687372985789027L;
	static final Logger LOG = LoggerFactory.getLogger(LabourWageInfo.class);
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@ManyToOne
	private ProjectLabour projectLabour;

	@NotNull
	@ManyToOne
	private WageType wageType;

	@Max(999999)
	@Min(1)
	@NotNull
	private Integer wageUnit;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date activationDate = new Date(System.currentTimeMillis());

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date lastValidDate = null;

	public LabourWageInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectLabour getProjectLabour() {
		return projectLabour;
	}

	public void setProjectLabour(ProjectLabour projectLabour) {
		this.projectLabour = projectLabour;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public WageType getWageType() {
		return wageType;
	}

	public void setWageType(WageType wageType) {
		this.wageType = wageType;
	}

	public Integer getWageUnit() {
		return wageUnit;
	}

	public void setWageUnit(Integer wageUnit) {
		this.wageUnit = wageUnit;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public Date getLastValidDate() {
		return lastValidDate;
	}

	public void setLastValidDate(Date lastValidDate) {
		this.lastValidDate = lastValidDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LabourWageInfo [id=" + id + ", version=" + version + ", projectLabour=" + projectLabour + ", wageType="
				+ wageType + ", wageUnit=" + wageUnit + ", activationDate=" + activationDate + ", lastValidDate="
				+ lastValidDate + "]";
	}

}
