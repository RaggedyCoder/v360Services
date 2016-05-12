package com.thevolume360.domain.enums;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
