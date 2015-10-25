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
	private String name;

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
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", name=" + name + ", materialType=" + materialType + "]";
	}

	

}
