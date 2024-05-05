package com.chatbot.assitant.api.domain.githubissues.service;

import com.chatbot.assitant.api.domain.githubissues.IGithubissuesApi;
import com.chatbot.assitant.api.domain.githubissues.model.aggregates.UnAnsweredIssuesAggregates;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Slf4j
public class GithubissuesApi implements IGithubissuesApi {
    private Logger logger = LoggerFactory.getLogger(GithubissuesApi.class);

    @Override
    public UnAnsweredIssuesAggregates queryUnAnsweredIssuesId(String repositoryName, String cookie) throws IOException {
        return null;
    }

    @Override
    public boolean answer(String repositoryName, String cookie, String issueId, String text) throws IOException {
        return false;
    }
}
