package com.xxx.wordingtech.model.feedback;

public class FeedbackRequestModel {
    String content;

    public FeedbackRequestModel(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
