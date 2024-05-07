package com.chatbot.assitant.api.domain.ai.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usage {
    private int completion_tokens;

    private int prompt_tokens;

    private int total_tokens;
}
