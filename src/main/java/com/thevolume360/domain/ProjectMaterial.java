package com.thevolume360.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class ProjectMaterial extends PersistentObject implements Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3760648498835661953L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@NotNull
	@ManyToOne
	private ProjectBuying projectBuying;

	@NotNull
	@ManyToOne
	private Material material;

	@NotNull
	private Long unitCost;

	@NotNull
	private Integer unit;

	private Long totalCost;

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

	public ProjectBuying getProjectBuying() {
		return projectBuying;
	}

	public void setProjectBuying(ProjectBuying projectBuying) {
		this.projectBuying = projectBuying;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Long getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Long unitCost) {
		this.unitCost = unitCost;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "ProjectMaterial [id=" + id + ", version=" + version + ", projectBuying=" + projectBuying + ", material="
				+ material + ", unitCost=" + unitCost + ", unit=" + unit + ", totalCost=" + totalCost
				+ ", getCreatedDate()=" + getCreatedDate() + ", getLastModifiedDate()=" + getLastModifiedDate()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getLastModifiedBy()=" + getLastModifiedBy() + "]";
	}

}
