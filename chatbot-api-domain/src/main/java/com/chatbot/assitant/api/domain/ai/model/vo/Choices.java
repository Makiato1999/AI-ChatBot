package com.chatbot.assitant.api.domain.ai.model.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Choices {
    private String finish_reason;

    private int index;

    private String logprobs;

    private String text;
}
