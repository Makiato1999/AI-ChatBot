package com.chatbot.assitant.api.domain.githubissues.model.req;

import lombok.Getter;
import lombok.Setter;

public class AnswerReq {
    private ReqData reqData;

    @Getter
    @Setter
    private String body;

    public AnswerReq(ReqData reqData) {
        this.reqData = reqData;
        this.body = reqData.getText();  // Initialize body based on ReqData
    }

    public ReqData getReqData() {
        return reqData;
    }

    public void setReqData(ReqData reqData) {
        this.reqData = reqData;
        this.body = reqData.getText();  // Update body whenever ReqData is updated
    }

}
