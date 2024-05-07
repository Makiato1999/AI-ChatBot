package com.chatbot.assitant.api.domain.githubissues.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chatbot.assitant.api.domain.githubissues.IGithubissuesApi;
import com.chatbot.assitant.api.domain.githubissues.model.aggregates.UnAnsweredIssuesAggregates;
import com.chatbot.assitant.api.domain.githubissues.model.req.AnswerReq;
import com.chatbot.assitant.api.domain.githubissues.model.req.ReqData;
import com.chatbot.assitant.api.domain.githubissues.model.res.AnswerRes;
import com.chatbot.assitant.api.domain.githubissues.model.res.RespData;
import com.chatbot.assitant.api.domain.githubissues.model.vo.Issue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class GithubissuesApi implements IGithubissuesApi {
    private Logger logger = LoggerFactory.getLogger(GithubissuesApi.class);

    @Value("${ChatBot-api.githubToken}")
    private String githubToken;

    @Override
    public UnAnsweredIssuesAggregates queryUnAnsweredIssuesId(String repositoryName) throws IOException {
        String TOKEN = githubToken;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // Create a HttpGet request
        HttpGet request = new HttpGet("https://api.github.com/repos/Makiato1999/"+repositoryName+"/issues");
        request.setHeader("Authorization", "token " + TOKEN);
        request.setHeader("Accept", "application/vnd.github.v3+json");

        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("Pull issue question data\n--> repositoryName：{} jsonStr：{}", repositoryName, jsonStr);
            List<Issue> issues = JSON.parseArray(jsonStr, Issue.class);
            UnAnsweredIssuesAggregates unAnsweredIssuesAggregates = new UnAnsweredIssuesAggregates(issues);
            return unAnsweredIssuesAggregates;
        } else {
            throw new RuntimeException("queryUnAnsweredIssuesId Err Code is " + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String repositoryName, int issueId, String text) throws IOException {
        String TOKEN = githubToken;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.github.com/repos/Makiato1999/"+repositoryName+"/issues/"+issueId+"/comments");
        post.setHeader("Authorization", "token " + TOKEN);
        post.setHeader("Accept", "application/vnd.github.v3+json");
        post.setHeader("Content-Type", "application/json; charset=UTF-8");
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");

//        String json = "{\"body\": \"" + "I don't know!" + "\"}";
//        post.setEntity(new StringEntity(json));

        AnswerReq answerReq = new AnswerReq(new ReqData(text));
        String paramJson = JSONObject.toJSONString(answerReq);
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode()  == HttpStatus.SC_CREATED) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("Pull issue answer data\n--> repositoryName：{} issueId：{} jsonStr：{}", repositoryName, issueId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isCommentPostedSuccessfully();
        } else {
            throw new RuntimeException("answer Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
