package com.thevolume360.web.editor;

import java.beans.PropertyEditorSupport;

import com.thevolume360.domain.enums.WageCategory;

public class WageCategoryEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		for (WageCategory wageCategory : WageCategory.values()) {
			if (wageCategory.name().equalsIgnoreCase(text)) {
				setValue(wageCategory);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		return String.valueOf(getValue());
	}
}
