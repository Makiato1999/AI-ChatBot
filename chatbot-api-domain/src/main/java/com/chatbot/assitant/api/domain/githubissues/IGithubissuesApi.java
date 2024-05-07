package com.chatbot.assitant.api.domain.githubissues;

import com.chatbot.assitant.api.domain.githubissues.model.aggregates.UnAnsweredIssuesAggregates;

import java.io.IOException;

public interface IGithubissuesApi {
    UnAnsweredIssuesAggregates queryUnAnsweredIssuesId(String repositoryName) throws IOException;

    boolean answer(String repositoryName, int issueId, String text) throws IOException;
}
