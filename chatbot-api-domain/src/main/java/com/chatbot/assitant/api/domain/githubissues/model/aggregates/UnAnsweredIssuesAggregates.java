package com.chatbot.assitant.api.domain.githubissues.model.aggregates;

import com.chatbot.assitant.api.domain.githubissues.model.req.ReqData;
import com.chatbot.assitant.api.domain.githubissues.model.res.RespData;
import com.chatbot.assitant.api.domain.githubissues.model.vo.Issue;

import java.util.ArrayList;
import java.util.List;

public class UnAnsweredIssuesAggregates {
    private RespData respData;

    public UnAnsweredIssuesAggregates(List<Issue> issues) {
        List<Issue> unAnsweredIssues = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getComments() == 0 && issue.getState().equals("open")) {
                unAnsweredIssues.add(issue);
            }
        }
        this.respData = new RespData();
        this.respData.setIssues(unAnsweredIssues);
    }

    public RespData getRespData() {
        return respData;
    }

    public void setRespData(RespData respData) {
        this.respData = respData;
    }

}

