package com.thevolume360.domain;

import java.util.Date;

import javax.persistence.Column;
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
public class LabourWorkInfo extends PersistentObject implements Auditable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4391786416464612669L;
	static final Logger LOG = LoggerFactory.getLogger(LabourWorkInfo.class);
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@ManyToOne
	private ProjectLabour projectLabour;

	@NotNull
	private boolean isPaid;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date workedDate;

	@Max(24)
	@Min(1)
	@Column(length = 2)
	private Integer workedHour;

	@ManyToOne
	private LabourPaymentInfo labourPaymentInfo;

	public LabourWorkInfo() {
	}

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

	public ProjectLabour getProjectLabour() {
		return projectLabour;
	}

	public void setProjectLabour(ProjectLabour projectLabour) {
		this.projectLabour = projectLabour;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getWorkedDate() {
		return workedDate;
	}

	public void setWorkedDate(Date workedDate) {
		this.workedDate = workedDate;
	}

	public Integer getWorkedHour() {
		return workedHour;
	}

	public void setWorkedHour(Integer workedHour) {
		this.workedHour = workedHour;
	}

	public LabourPaymentInfo getLabourPaymentInfo() {
		return labourPaymentInfo;
	}

	public void setLabourPaymentInfo(LabourPaymentInfo labourPaymentInfo) {
		this.labourPaymentInfo = labourPaymentInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LabourWorkInfo [id=" + id + ", version=" + version + ", projectLabour=" + projectLabour + ", isPaid="
				+ isPaid + ", workedDate=" + workedDate + ", workedHour=" + workedHour + ", labourPaymentInfo="
				+ labourPaymentInfo + "]";
	}

}
