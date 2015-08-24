package com.thevolume360.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;
import com.thevolume360.domain.enums.District;
import com.thevolume360.utils.StringUtils;

@Embeddable
public class ProjectAddress {

	@Size(max = 255)
	@Column(length = 255)
	@NotEmpty
	@Expose
	private String locationAddress;

	@Size(max = 5)
	@Column(length = 5)
	@NotEmpty
	@Expose
	private String postalCode;

	@NotNull
	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	@Expose
	private District district;

	public ProjectAddress() {
		super();
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (StringUtils.isNotEmpty(locationAddress))
			builder.append(locationAddress).append(" ");
		if (district != null)
			builder.append(district.getName()).append(" ");
		if (StringUtils.isNotEmpty(postalCode))
			builder.append(postalCode).append(" ");
		return builder.toString();
	}
}
