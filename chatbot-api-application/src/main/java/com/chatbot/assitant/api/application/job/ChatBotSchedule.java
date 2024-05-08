package com.chatbot.assitant.api.application.job;

import com.alibaba.fastjson.JSON;
import com.chatbot.assitant.api.domain.ai.IOpenAI;
import com.chatbot.assitant.api.domain.githubissues.IGithubissuesApi;
import com.chatbot.assitant.api.domain.githubissues.model.aggregates.UnAnsweredIssuesAggregates;
import com.chatbot.assitant.api.domain.githubissues.model.vo.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;


@EnableScheduling
@Configuration
public class ChatBotSchedule {
    private Logger logger = LoggerFactory.getLogger(ChatBotSchedule.class);

    @Value("${ChatBot-api.repositoryName}")
    private String repositoryName;

    @Resource
    private IGithubissuesApi githubissuesApi;
    @Resource
    private IOpenAI openAI;

    @Scheduled(cron = "0 0/1 * * * ? *")
    public void run() {
        try {
            // 1. search unanswered issues from GitHub
            UnAnsweredIssuesAggregates unAnsweredIssuesAggregates = githubissuesApi.queryUnAnsweredIssuesId(repositoryName);
            List<Issue> issues = unAnsweredIssuesAggregates.getRespData().getIssues();
            logger.info("Pull unanswered issues question:\n--> {}", issues);


            if (issues == null || issues.isEmpty()) {
                logger.info("No unanswered issues were found in this search");
                return;
            }

//            // 2. get ChatGPT answers
//            Issue issue = issues.get(0);
//            String ans = openAI.askForChatGPT(issue.getBody().trim());
//
//            // 3. post AI answers on GitHub issues
//            githubissuesApi.answer(repositoryName, issue.getNumber(), ans);
//            logger.info("TestResult: \n--> {}", JSON.toJSONString(unAnsweredIssuesAggregates));

        } catch (Exception e) {

        }
    }
}
