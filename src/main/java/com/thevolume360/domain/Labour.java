package com.thevolume360.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.Expose;
import com.thevolume360.domain.enums.Gender;

/*
 -- -----------------------------------------------------
 -- Table `volume360`.`labour`
 -- -----------------------------------------------------
 DROP TABLE IF EXISTS `volume360`.`labour` ;

 CREATE TABLE IF NOT EXISTS `volume360`.`labour` (
 `id` BIGINT(20) NOT NULL,
 `created_date` DATETIME NOT NULL,
 `last_modfied_date` DATETIME NOT NULL,
 `first_name` VARCHAR(100) NOT NULL,
 `last_name` VARCHAR(100) NOT NULL,
 `gender` VARCHAR(6) NOT NULL,
 `contact_number` VARCHAR(32) NOT NULL,
 `created_by` BIGINT(20) NULL DEFAULT NULL,
 `last_modified_by` BIGINT(20) NULL DEFAULT NULL,
 `version` VARCHAR(30) NULL,
 PRIMARY KEY (`labour_id`),
 INDEX `fk_labour_user_created_by_idx` (`created_by` ASC),
 INDEX `fk_labour_user_last_modified_by_idx` (`last_modified_by` ASC),
 CONSTRAINT `fk_labour_user_created_by`
 FOREIGN KEY (`created_by`)
 REFERENCES `volume360`.`user` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION,
 CONSTRAINT `fk_labour_user_last_modified_by`
 FOREIGN KEY (`last_modified_by`)
 REFERENCES `volume360`.`user` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION)
 ENGINE = InnoDB;
 */
@Entity
public class Labour extends PersistentObject implements Auditable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9168009106329735160L;

	static final Logger LOG = LoggerFactory.getLogger(Labour.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	private Long id;

	@Version
	@Expose
	private Long version;

	@Valid
	@Embedded
	@Expose
	private FullName fullName;

	@NotNull
	@Column(length = 6)
	@Enumerated(EnumType.STRING)
	@Expose
	private Gender gender;

	@NotEmpty
	@Size(max = 32)
	@Column(length = 32)
	@Pattern(regexp = "^01(1|5|6|7|8|9)\\d{8}$", message = "Phone number must be valid.")
	@Expose
	private String contactNumber;

	@OneToMany(mappedBy = "labour", fetch = FetchType.LAZY)
	@Expose
	private Set<ProjectLabour> projectLabours = new HashSet<>();

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the projectLabours
	 */
	public Set<ProjectLabour> getProjectLabours() {
		return projectLabours;
	}

	/**
	 * @param projectLabours
	 *            the projectLabours to set
	 */
	public void setProjectLabours(Set<ProjectLabour> projectLabours) {
		this.projectLabours = projectLabours;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Labour)) {
			return false;
		}
		Labour other = (Labour) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Labour [id=" + id + ", version=" + version + ", fullName="
				+ fullName + ", gender=" + gender + ", contactNumber="
				+ contactNumber + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

}
