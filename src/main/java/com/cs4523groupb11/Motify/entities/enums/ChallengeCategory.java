package com.cs4523groupb11.Motify.entities.enums;

public enum ChallengeCategory {
    LEARNING("LEARNING"),
    FITNESS("FITNESS"),
    CREATIVITY("CREATIVITY"),
    PRODUCTIVITY("PRODUCTIVITY"),
    SOCIAL("SOCIAL"),
    FINANCIAL("FINANCIAL"),
    CAREER("CAREER"),
    GROWTH("GROWTH");

    private String category;

    ChallengeCategory(String category) {
        this.category = category;
    }

    public String setCategory() {
        return category;
    }

}
