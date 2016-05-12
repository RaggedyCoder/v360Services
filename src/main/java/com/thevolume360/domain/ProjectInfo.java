package com.thevolume360.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.thevolume360.domain.enums.Status;
import com.thevolume360.domain.enums.WorkType;

/*
 -- -----------------------------------------------------
 -- Table `thevolume360`.`project_info`
 -- -----------------------------------------------------
 DROP TABLE IF EXISTS `thevolume360`.`project_info` ;

 CREATE TABLE IF NOT EXISTS `thevolume360`.`project_info` (
 `id` BIGINT(20) NOT NULL,
 `project_work_id` VARCHAR(50) NOT NULL,
 `project_name` VARCHAR(500) NOT NULL,
 `location_address` VARCHAR(255) NOT NULL,
 `district` VARCHAR(32) NOT NULL,
 `postal_code` VARCHAR(5) NOT NULL,
 `date_taken` DATETIME NOT NULL,
 `complete_date_estimated` DATETIME NULL DEFAULT NULL,
 `project_budget` BIGINT(9) NOT NULL,
 `comment` VARCHAR(1000) NULL DEFAULT NULL,
 `version` BIGINT(20) NULL DEFAULT NULL,
 `created_date` DATETIME NOT NULL,
 `last_modified_date` DATETIME NOT NULL,
 `created_by` BIGINT(20) NULL DEFAULT NULL,
 `last_modified_by` BIGINT(20) NULL DEFAULT NULL,
 `status` VARCHAR(10) NOT NULL,
 PRIMARY KEY (`id`))
 ENGINE = InnoDB;
 */
@Entity
public class ProjectInfo extends PersistentObject implements Auditable {
	static final Logger LOG = LoggerFactory.getLogger(ProjectInfo.class);
	/**
	 * 
	 */
	static final long serialVersionUID = 824637286554074321L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Size(max = 500, min = 4)
	@Column(length = 500)
	private String projectName;

	@Size(max = 50)
	@Column(length = 50)

	private String projectWorkId;

	@NotNull
	@ManyToOne
	private Client client;

	@Version
	private Long version;

	@Valid
	@Embedded

	private ProjectAddress projectAddress;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateTaken;

	@NotNull
	@Future(message = "date must have to be a future date.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date completeDateEstimated;

	@NotNull
	@Max(999999999)
	@Min(1)
	@Column(length = 9)

	private Long projectBudget;

	@NotNull
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Status status;

	@NotNull
	@Column(length = 10)
	@Enumerated(EnumType.STRING)

	private WorkType workType;

	@Column(length = 1000)

	private String comment;

	@OneToMany(mappedBy = "projectInfo", fetch = FetchType.LAZY)

	private Set<ProjectLabour> projectLabours = new HashSet<>();

	@OneToMany(mappedBy = "projectInfo")
	private Set<ProjectBuying> projectBuyings = new HashSet<>();

	@OneToMany(mappedBy = "projectInfo")
	private Set<ClientInvestment> clientInvestments = new HashSet<>();

	@OneToMany(mappedBy = "projectInfo")
	private Set<BankWithdraw> bankWithdraws = new HashSet<>();

	@OneToMany(mappedBy="projectInfo",cascade = CascadeType.ALL, fetch = FetchType.EAGER)	
	private Set<Attachment> attachment = new HashSet<>();

	public ProjectInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectWorkId() {
		return projectWorkId;
	}

	public void setProjectWorkId(String projectWorkId) {
		this.projectWorkId = projectWorkId;
	}

	public ProjectAddress getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(ProjectAddress projectAddress) {
		this.projectAddress = projectAddress;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}

	public Date getCompleteDateEstimated() {
		return completeDateEstimated;
	}

	public void setCompleteDateEstimated(Date completeDateEstimated) {
		this.completeDateEstimated = completeDateEstimated;
	}

	public Long getProjectBudget() {
		return projectBudget;
	}

	public void setProjectBudget(Long projectBudget) {
		this.projectBudget = projectBudget;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<ProjectLabour> getProjectLabours() {
		return projectLabours;
	}

	public void setProjectLabours(Set<ProjectLabour> projectLabours) {
		this.projectLabours = projectLabours;
	}

	public Set<ProjectBuying> getProjectBuyings() {
		return projectBuyings;
	}

	public void setProjectBuyings(Set<ProjectBuying> projectBuyings) {
		this.projectBuyings = projectBuyings;
	}

	public Set<ClientInvestment> getClientInvestments() {
		return clientInvestments;
	}

	public void setClientInvestments(Set<ClientInvestment> clientInvestments) {
		this.clientInvestments = clientInvestments;
	}

	public Set<BankWithdraw> getBankWithdraws() {
		return bankWithdraws;
	}

	public void setBankWithdraws(Set<BankWithdraw> bankWithdraws) {
		this.bankWithdraws = bankWithdraws;
	}
	
	

	public Set<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(Set<Attachment> attachment) {
		this.attachment = attachment;
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
		return "ProjectInfo [id=" + id + ", projectName=" + projectName + ", projectWorkId=" + projectWorkId
				+ ", client=" + client + ", version=" + version + ", projectAddress=" + projectAddress + ", dateTaken="
				+ dateTaken + ", completeDateEstimated=" + completeDateEstimated + ", projectBudget=" + projectBudget
				+ ", status=" + status + ", workType=" + workType + ", comment=" + comment + ", projectLabours="
				+ projectLabours + ", projectBuyings=" + projectBuyings + ", clientInvestments=" + clientInvestments
				+ ", bankWithdraws=" + bankWithdraws + "]";
	}

}
