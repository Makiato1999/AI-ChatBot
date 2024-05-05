package com.chatbot.assitant.api.domain.githubissues.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Issue {
    private String url;
    private String repositoryUrl;
    private String labelsUrl;
    private String commentsUrl;
    private String eventsUrl;
    private String htmlUrl;
    private long id;
    private String nodeId;
    private int number;
    private String title;
    private User user;
    private List<Object> labels;
    private String state;
    private boolean locked;
    private Object assignee;
    private List<Object> assignees;
    private Object milestone;
    private int comments;
    private String createdAt;
    private String updatedAt;
    private Object closedAt;
    private String authorAssociation;
    private Object activeLockReason;
    private String body;
    private Reactions reactions;
    private String timelineUrl;
    private Object performedViaGithubApp;
    private Object stateReason;
}
