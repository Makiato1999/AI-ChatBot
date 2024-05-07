package com.chatbot.assitant.api.domain.ai.model.aggregates;

import com.chatbot.assitant.api.domain.ai.model.vo.Choices;

import java.util.List;

public class AIAnswer {
    private List<Choices> choices;

    public AIAnswer(List<Choices> choices) {
        this.choices = choices;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
}
