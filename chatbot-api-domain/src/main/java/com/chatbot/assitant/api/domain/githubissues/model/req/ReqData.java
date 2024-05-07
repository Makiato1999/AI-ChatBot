package com.chatbot.assitant.api.domain.githubissues.model.req;

public class ReqData {
    private String text;

    public ReqData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
