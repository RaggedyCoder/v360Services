package com.thevolume360.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.thevolume360.domain.enums.WageType;

public class IntakeLabours {

	@NotNull
	@Enumerated(EnumType.STRING)
	private WageType wageType;

	@NotNull
	private Integer wageUnit;
	@NotNull
	private List<Labour> labours = new ArrayList<>();

	/**
	 * @return the wageType
	 */
	public WageType getWageType() {
		return wageType;
	}

	/**
	 * @param wageType
	 *            the wageType to set
	 */
	public void setWageType(WageType wageType) {
		this.wageType = wageType;
	}

	/**
	 * @return the wageUnit
	 */
	public Integer getWageUnit() {
		return wageUnit;
	}

	/**
	 * @param wageUnit
	 *            the wageUnit to set
	 */
	public void setWageUnit(Integer wageUnit) {
		this.wageUnit = wageUnit;
	}

	/**
	 * @return the labours
	 */
	public List<Labour> getLabours() {
		return labours;
	}

	/**
	 * @param labours
	 *            the labours to set
	 */
	public void setLabours(List<Labour> labours) {
		this.labours = labours;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IntakeLabours [wageType=" + wageType + ", wageUnit=" + wageUnit
				+ ", labours=" + labours + "]";
	}

}
