package com.cs4523groupb11.Motify.DTO.response_non_entity;



public class MessageResponse {
    private String content;

    public MessageResponse(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
}
