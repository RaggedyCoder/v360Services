package com.thevolume360.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thevolume360.domain.enums.ClientType;

@Entity
public class Client extends PersistentObject implements Auditable {
	static final Logger LOG = LoggerFactory.getLogger(Client.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091224988745174732L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Size(min = 4, max = 100)
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ClientType clientType;

	@Version
	private Long version;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<ProjectInfo> projectInfos = new HashSet<>();

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<ClientInvestment> clientInvestments = new HashSet<>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the projectInfos
	 */
	public Set<ProjectInfo> getProjectInfos() {
		return projectInfos;
	}

	/**
	 * @param projectInfos
	 *            the projectInfos to set
	 */
	public void setProjectInfos(Set<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

	/**
	 * @return the clientInvestments
	 */
	public Set<ClientInvestment> getClientInvestments() {
		return clientInvestments;
	}

	/**
	 * @param clientInvestments
	 *            the clientInvestments to set
	 */
	public void setClientInvestments(Set<ClientInvestment> clientInvestments) {
		this.clientInvestments = clientInvestments;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the clientType
	 */
	public ClientType getClientType() {
		return clientType;
	}

	/**
	 * @param clientType
	 *            the clientType to set
	 */
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

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
		Client other = (Client) obj;
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
		return "Client [id=" + id + ", name=" + name + ", clientType=" + clientType + ", version=" + version + "]";
	}

}
