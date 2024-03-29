package com.cs4523groupb11.Motify.entities.enums;

public enum TimeUnit {
    MINUTE("MINUTE"),
    HOUR("HOUR"),
    DAY("DAY"),
    WEEK("WEEK"),
    MONTH("MONTH"),
    YEAR("YEAR");

    private final String unit;

    TimeUnit(String unit) {
        this.unit = unit;
    }

    public String getDisplayName() {
        return unit;
    }
}
