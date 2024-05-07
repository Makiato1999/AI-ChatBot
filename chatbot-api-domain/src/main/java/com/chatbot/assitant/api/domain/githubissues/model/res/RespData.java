package com.chatbot.assitant.api.domain.githubissues.model.res;

import com.chatbot.assitant.api.domain.githubissues.model.vo.Issue;

import java.util.List;

public class RespData {
    private List<Issue> issues;

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
