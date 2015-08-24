package com.thevolume360.domain.enums;

public enum Designation {
    DUMMY("Dummy"), MUMMY("Mummy");

    String label;

    Designation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
