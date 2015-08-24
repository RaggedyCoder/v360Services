package com.thevolume360.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.thevolume360.domain.enums.MaterialType;

@Entity
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String materialName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private MaterialType materialType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", materialName=" + materialName + "]";
	}

}
