package com.thevolume360.web.editor;

import java.beans.PropertyEditorSupport;

import com.thevolume360.domain.enums.WageType;

public class WageTypeEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		for (WageType wageType : WageType.values()) {
			if (wageType.getName().equalsIgnoreCase(text)) {
				setValue(wageType);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		return String.valueOf(getValue());
	}
}
