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
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thevolume360.domain.enums.WageCategory;

@Entity
public class WageType {
	static final Logger LOG = LoggerFactory.getLogger(WageType.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Enumerated(EnumType.STRING)
	private WageCategory wageCategory;

	public WageType() {
		super();
	}

	public WageType(Long id, String name, WageCategory wageCategory) {
		this.id = id;
		this.name = name;
		this.wageCategory = wageCategory;
	}

	@OneToMany(mappedBy = "wageType", fetch = FetchType.LAZY)
	private Set<LabourWageInfo> labourWageInfos = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WageCategory getWageCategory() {
		return wageCategory;
	}

	public void setWageCategory(WageCategory wageCategory) {
		this.wageCategory = wageCategory;
	}

	@Override
	public String toString() {
		return "WageType [id=" + id + ", name=" + name + ", wageCategory=" + wageCategory + ", labourWageInfos="
				+ labourWageInfos + "]";
	}

}
