package com.chatbot.assitant.api.domain.githubissues.model.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRes {
    private String body;

    public boolean isCommentPostedSuccessfully() {
        return this.body != null && !this.body.isEmpty(); // 确认body字段不为空
    }
}
