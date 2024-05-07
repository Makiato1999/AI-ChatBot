package com.chatbot.assitant.api.domain.githubissues.model.res;

public class AnswerRes {
    private String body;

    public boolean isCommentPostedSuccessfully() {
        return body != null && !body.isEmpty(); // 确认body字段不为空
    }
}
