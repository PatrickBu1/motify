package com.cs4523groupb11.Motify.DTO.request_non_entity;

import java.time.Duration;

public class CheckInRequest {

    String challengeId;
    Integer amount;
    Duration duration;

    public CheckInRequest(String challengeId, Integer amount, Duration duration) {
        this.challengeId = challengeId;
        this.amount = amount;
        this.duration = duration;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
