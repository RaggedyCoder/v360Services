package com.thevolume360.domain.enums;

public enum YesNo {
    YES("Yes"), No("No");

    private final String label;

    YesNo(String value) {
        this.label = value;
    }

    public String getLabel() {
        return label;
    }
}
