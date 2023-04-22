package com.cs4523groupb11.Motify.entities.enums;

public enum ChallengeCategory {
    EATING("EATING"),
    EXERCISE("EXERCISE"),
    FINANCE("FINANCE"),
    MEDITATION("MEDITATION"),
    PROFESSIONAL("PROFESSIONAL"),
    READING("READING"),
    SLEEPING("SLEEPING"),
    SOCIAL("SOCIAL"),
    STUDYING("STUDYING"),
    WRITING("WRITING"),
    PRODUCTIVITY("PRODUCTIVITY"),
    CREATIVITY("CREATIVITY"),
    FAMILY("FAMILY"),
    OTHER("OTHER");

    private String category;

    ChallengeCategory(String category) {
        this.category = category;
    }

    public String setCategory() {
        return category;
    }

}
