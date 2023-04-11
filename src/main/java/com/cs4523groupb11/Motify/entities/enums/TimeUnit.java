package com.cs4523groupb11.Motify.entities.enums;

public enum TimeUnit {
    MINUTE("Minute"),
    HOUR("Hour"),
    DAY("Day"),
    WEEK("Week"),
    MONTH("Month");

    private final String unit;

    TimeUnit(String unit) {
        this.unit = unit;
    }

    public String getDisplayName() {
        return unit;
    }
}
