package com.thevolume360.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.thevolume360.domain.enums.InvestmentType;

@Entity
public class ClientInvestment extends PersistentObject implements Auditable {
	static final Logger LOG = LoggerFactory.getLogger(ClientInvestment.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 5287570408494814801L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;
	@NotNull
	@ManyToOne
	private ProjectInfo projectInfo;

	@NotNull
	@ManyToOne
	private Client client;

	@NotNull
	@Max(999999999)
	@Min(1)
	@Column(length = 9)
	private Long investedAmount;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date investmentDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private InvestmentType investmentType;

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

	public Long getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Long investedAmount) {
		this.investedAmount = investedAmount;
	}

	public Date getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}

	public InvestmentType getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClientInvestment [id=" + id + ", version=" + version
				+ ", projectInfo=" + projectInfo + ", client=" + client
				+ ", investedAmount=" + investedAmount + ", investmentDate="
				+ investmentDate + ", investmentType=" + investmentType + "]";
	}

}
