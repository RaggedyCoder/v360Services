package com.thevolume360.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

@Embeddable
public class FullName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2437319874248074034L;

	@NotEmpty
	@Size(min = 4, max = 100, message = "first name must contains 4 to 100 letters.")
	@Column(length = 100)
	@Expose
	private String firstName;

	@NotEmpty
	@Size(min = 4, max = 100, message = "last name must contains 4 to 100 letters.")
	@Column(length = 100)
	@Expose
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(firstName).append(" ")
				.append(lastName).toString();
	}

}
