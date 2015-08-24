package com.thevolume360.web.editor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thevolume360.domain.enums.Role;

import java.beans.PropertyEditorSupport;

public class AuthorityEditor extends PropertyEditorSupport {
	private static final Logger log = LoggerFactory.getLogger(AuthorityEditor.class);

	@Override
	public void setAsText(String text) {
		for (Role authority : Role.values()) {
			if (authority.name().equalsIgnoreCase(text)) {
				setValue(authority);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		log.info("AuthorityEditor value ={}", getValue());
		return String.valueOf(getValue());
	}
}
