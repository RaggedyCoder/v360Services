package com.thevolume360.web.editor;

import java.beans.PropertyEditorSupport;

import com.thevolume360.domain.enums.WorkType;

public class WorkTypeEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		for (WorkType workType : WorkType.values()) {
			if (workType.getName().equalsIgnoreCase(text)) {
				setValue(workType);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		return String.valueOf(getValue());
	}
}
