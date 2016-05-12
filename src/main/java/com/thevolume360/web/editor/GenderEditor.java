package com.thevolume360.web.editor;

import com.thevolume360.domain.enums.Gender;

import java.beans.PropertyEditorSupport;

public class GenderEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.getLabel().equalsIgnoreCase(text)) {
                setValue(gender);
                break;
            }
        }
    }

    @Override
    public String getAsText() {
        return String.valueOf(getValue());
    }
}
