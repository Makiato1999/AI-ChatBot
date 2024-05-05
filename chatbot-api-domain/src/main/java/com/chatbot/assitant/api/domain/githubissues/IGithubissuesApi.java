package com.chatbot.assitant.api.domain.githubissues;

import com.chatbot.assitant.api.domain.githubissues.model.aggregates.UnAnsweredIssuesAggregates;

import java.io.IOException;

public interface IGithubissuesApi {
    UnAnsweredIssuesAggregates queryUnAnsweredIssuesId(String repositoryName, String cookie) throws IOException;

    boolean answer(String repositoryName, String cookie, String issueId, String text) throws IOException;
}
