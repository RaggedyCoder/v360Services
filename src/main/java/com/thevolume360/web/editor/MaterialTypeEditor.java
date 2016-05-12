package com.thevolume360.web.editor;

import java.beans.PropertyEditorSupport;

import com.thevolume360.domain.enums.MaterialType;

public class MaterialTypeEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		for (MaterialType materialType : MaterialType.values()) {
			if (materialType.name().equalsIgnoreCase(text)) {
				setValue(materialType);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		return String.valueOf(getValue());
	}
}
