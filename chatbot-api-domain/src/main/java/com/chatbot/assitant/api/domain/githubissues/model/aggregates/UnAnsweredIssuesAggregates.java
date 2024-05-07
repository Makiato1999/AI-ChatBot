package com.chatbot.assitant.api.domain.githubissues.model.aggregates;

import com.chatbot.assitant.api.domain.githubissues.model.req.ReqData;
import com.chatbot.assitant.api.domain.githubissues.model.res.RespData;
import com.chatbot.assitant.api.domain.githubissues.model.vo.Issue;

import java.util.List;

public class UnAnsweredIssuesAggregates {
    private RespData respData;

    public UnAnsweredIssuesAggregates(List<Issue> issues) {
        this.respData = new RespData();
        this.respData.setIssues(issues);
    }

    public RespData getRespData() {
        return respData;
    }

    public void setRespData(RespData respData) {
        this.respData = respData;
    }

}

