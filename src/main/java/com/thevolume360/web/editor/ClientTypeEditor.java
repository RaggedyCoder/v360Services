package com.thevolume360.web.editor;

import java.beans.PropertyEditorSupport;

import com.thevolume360.domain.enums.ClientType;

public class ClientTypeEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		for (ClientType clientType : ClientType.values()) {			
			if (clientType.name().equalsIgnoreCase(text)) {
				setValue(clientType);
				break;
			}
		}
	}

	@Override
	public String getAsText() {
		return String.valueOf(getValue());
	}
}
