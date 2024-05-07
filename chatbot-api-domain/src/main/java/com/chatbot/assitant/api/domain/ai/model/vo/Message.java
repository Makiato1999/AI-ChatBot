package com.chatbot.assitant.api.domain.ai.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String role;

    private String content;
}
