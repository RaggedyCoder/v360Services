package com.thevolume360.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.thevolume360.domain.enums.Designation;

@Entity
public class OfficeWorker extends PersistentObject implements Auditable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -906034414352963503L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@Valid
	@Embedded
	private FullName fullName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Designation designation;

	@NotEmpty
	@Size(max = 45)
	@Column(length = 45)
	@Pattern(regexp = "^01(1|5|6|7|8|9)\\d{8}$", message = "Phone number must be valid.")
	private String contactNumber;

	@OneToMany(mappedBy = "issuedBy")
	private Set<BankWithdraw> issuedBankWithDrawals = new HashSet<>();

	@OneToMany(mappedBy = "withdrawnBy")
	private Set<BankWithdraw> withrawedWithDrawals = new HashSet<>();

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

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Set<BankWithdraw> getIssuedBankWithDrawals() {
		return issuedBankWithDrawals;
	}

	public void setIssuedBankWithDrawals(Set<BankWithdraw> issuedBankWithDrawals) {
		this.issuedBankWithDrawals = issuedBankWithDrawals;
	}

	public Set<BankWithdraw> getWithrawedWithDrawals() {
		return withrawedWithDrawals;
	}

	public void setWithrawedWithDrawals(Set<BankWithdraw> withrawedWithDrawals) {
		this.withrawedWithDrawals = withrawedWithDrawals;
	}

	@Override
	public String toString() {
		return "OfficeWorker [id=" + id + ", version=" + version + ", fullName=" + fullName + ", designation="
				+ designation + ", contactNumber=" + contactNumber + ", issuedBankWithDrawals=" + issuedBankWithDrawals
				+ ", withrawedWithDrawals=" + withrawedWithDrawals + "]";
	}
	
	

}
