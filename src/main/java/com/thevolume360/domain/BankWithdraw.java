package com.thevolume360.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class BankWithdraw extends PersistentObject implements Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8278573776011195120L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private ProjectInfo projectInfo;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private OfficeWorker issuedBy;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private OfficeWorker withdrawnBy;

	@NotNull
	@Max(999999999)
	@Min(1)
	@Column(length = 9)
	private Long amount;

	@NotNull
	@Future(message = "date must have to be a future date.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date withdrawnDate;

	@NotNull
	private String chequeNumber;

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

	public OfficeWorker getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(OfficeWorker issuedBy) {
		this.issuedBy = issuedBy;
	}

	public OfficeWorker getWithdrawnBy() {
		return withdrawnBy;
	}

	public void setWithdrawnBy(OfficeWorker withdrawnBy) {
		this.withdrawnBy = withdrawnBy;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getWithdrawnDate() {
		return withdrawnDate;
	}

	public void setWithdrawnDate(Date withdrawnDate) {
		this.withdrawnDate = withdrawnDate;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	@Override
	public String toString() {
		return "BankWithdraw [id=" + id + ", version=" + version
				+ ", projectInfo=" + projectInfo + ", issuedBy=" + issuedBy
				+ ", withdrawnBy=" + withdrawnBy + ", amount=" + amount
				+ ", withdrawnDate=" + withdrawnDate + ", chequeNumber="
				+ chequeNumber + ", getCreatedDate()=" + getCreatedDate()
				+ ", getLastModifiedDate()=" + getLastModifiedDate()
				+ ", getCreatedBy()=" + getCreatedBy()
				+ ", getLastModifiedBy()=" + getLastModifiedBy() + "]";
	}

}
