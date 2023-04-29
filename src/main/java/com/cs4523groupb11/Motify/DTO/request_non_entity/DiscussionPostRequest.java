package com.cs4523groupb11.Motify.DTO.request_non_entity;

public class DiscussionPostRequest {

    private String content;

    private String challengeId;


    public DiscussionPostRequest(){}
    public DiscussionPostRequest(String content, String challengeId){
        this.content = content;
        this.challengeId = challengeId;
    }


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
