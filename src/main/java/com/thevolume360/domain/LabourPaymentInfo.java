package com.thevolume360.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class LabourPaymentInfo extends PersistentObject implements Auditable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6303050523571101589L;
	static final Logger LOG = LoggerFactory.getLogger(LabourPaymentInfo.class);
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@Max(999999)
	@Min(1)
	@NotNull
	private Integer normalPaidAmount;

	@Max(999999)
	@Min(1)
	private Integer overtimePaidAmount;

	@Max(999999)
	@Min(1)
	private Integer extraPaidAmount;

	@Max(999999)
	@Min(1)
	private Integer totalPaidAmount;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date paidDate;

	@OneToMany(mappedBy = "labourPaymentInfo")
	private Set<LabourWorkInfo> labourWorkInfos = new HashSet<>();

	public LabourPaymentInfo() {
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

	public Integer getNormalPaidAmount() {
		return normalPaidAmount;
	}

	public void setNormalPaidAmount(Integer normalPaidAmount) {
		this.normalPaidAmount = normalPaidAmount;
	}

	public Integer getOvertimePaidAmount() {
		return overtimePaidAmount;
	}

	public void setOvertimePaidAmount(Integer overtimePaidAmount) {
		this.overtimePaidAmount = overtimePaidAmount;
	}

	public Integer getExtraPaidAmount() {
		return extraPaidAmount;
	}

	public void setExtraPaidAmount(Integer extraPaidAmount) {
		this.extraPaidAmount = extraPaidAmount;
	}

	public Integer getTotalPaidAmount() {
		return totalPaidAmount;
	}

	public void setTotalPaidAmount(Integer totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Set<LabourWorkInfo> getLabourWorkInfos() {
		return labourWorkInfos;
	}

	public void setLabourWorkInfos(Set<LabourWorkInfo> labourWorkInfos) {
		this.labourWorkInfos = labourWorkInfos;
	}

	@Override
	public String toString() {
		return "LabourPaymentInfo [id=" + id + ", version=" + version + ", normalPaidAmount=" + normalPaidAmount
				+ ", overtimePaidAmount=" + overtimePaidAmount + ", extraPaidAmount=" + extraPaidAmount
				+ ", totalPaidAmount=" + totalPaidAmount + ", paidDate=" + paidDate + ", labourWorkInfos="
				+ labourWorkInfos + ", getCreatedDate()=" + getCreatedDate() + ", getLastModifiedDate()="
				+ getLastModifiedDate() + ", getCreatedBy()=" + getCreatedBy() + ", getLastModifiedBy()="
				+ getLastModifiedBy() + "]";
	}

}
