package com.cs4523groupb11.Motify.DTO.request_non_entity;

public class DiscussionPostRequest {
    private String ownerId;

    private String content;

    private String challengeId;


    public DiscussionPostRequest(){}
    public DiscussionPostRequest(String ownerId, String content, String challengeId){
        this.ownerId = ownerId;
        this.content = content;
        this.challengeId = challengeId;
    }

    public String getOwnerId() {return ownerId;}
    public void setOwnerId(String ownerId) {this.ownerId = ownerId;}

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getChallengeId(){
        return challengeId;
    }
    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }


}
