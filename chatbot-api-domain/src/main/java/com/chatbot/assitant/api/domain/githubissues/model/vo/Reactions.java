package com.chatbot.assitant.api.domain.githubissues.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reactions {
    private String url;
    private int totalCount;
    private int plusOne;
    private int minusOne;
    private int laugh;
    private int hooray;
    private int confused;
    private int heart;
    private int rocket;
    private int eyes;
}
