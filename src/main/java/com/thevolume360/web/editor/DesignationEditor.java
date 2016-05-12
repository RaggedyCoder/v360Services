package com.thevolume360.web.editor;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thevolume360.domain.enums.Designation;

public class DesignationEditor extends PropertyEditorSupport {
	private static final Logger log = LoggerFactory.getLogger(DesignationEditor.class);

	@Override
	public void setAsText(String text) {
		for (Designation designation : Designation.values()) {
			if (designation.name().equalsIgnoreCase(text)) {
				setValue(designation);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		log.info("DesignationEditor value ={}", getValue());
		return String.valueOf(getValue());
	}
}
