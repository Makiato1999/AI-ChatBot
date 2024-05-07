package com.chatbot.assitant.api.domain.ai.model.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Choices {
    private int index;

    private Message message;

    private String logprobs;

    private String finish_reason;
}
