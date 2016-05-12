package com.thevolume360.web.editor;

import java.beans.PropertyEditorSupport;

import com.thevolume360.domain.enums.InvestmentType;

public class InvestmentTypeEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		for (InvestmentType investmentType : InvestmentType.values()) {			
			if (investmentType.name().equalsIgnoreCase(text)) {
				setValue(investmentType);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		return String.valueOf(getValue());
	}
}
