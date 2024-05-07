package com.chatbot.assitant.test;


import com.alibaba.fastjson.JSON;
import com.chatbot.assitant.api.ApiApplication;
import com.chatbot.assitant.api.domain.githubissues.IGithubissuesApi;
import com.chatbot.assitant.api.domain.githubissues.model.aggregates.UnAnsweredIssuesAggregates;
import com.chatbot.assitant.api.domain.githubissues.model.vo.Issue;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${ChatBot-api.repositoryName}")
    private String repositoryName;

    @Resource
    private IGithubissuesApi githubissuesApi;

    @Test
    public void test_githubissuesApi() throws IOException {
        UnAnsweredIssuesAggregates unAnsweredIssuesAggregates = githubissuesApi.queryUnAnsweredIssuesId(repositoryName);
        logger.info("TestResult:\n--> {}", JSON.toJSONString(unAnsweredIssuesAggregates));

        List<Issue> issues = unAnsweredIssuesAggregates.getRespData().getIssues();
        for (Issue issue : issues) {
            int issueId = issue.getNumber();
            String title = issue.getTitle();
            logger.info("issueId：{} title：{}", issueId, title);

            // Answer question
            githubissuesApi.answer(repositoryName, issueId, title);
        }
    }


    // OpenAI
}
