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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


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

    @Scheduled(cron = "0/5 * * * * ?")
    public void run() {
        try {
            // 随机打烊中，避免风控
            if (new Random().nextBoolean()) {
                logger.info("Take a rest at random...");
                return;
            }
//            GregorianCalendar calendar = new GregorianCalendar();
//            int hour = calendar.get(Calendar.HOUR_OF_DAY);
//            if (hour > 22 || hour < 6) {
//                logger.info("AI is taking a rest!");
//                return;
//            }

            // 1. search unanswered issues from GitHub
            UnAnsweredIssuesAggregates unAnsweredIssuesAggregates = githubissuesApi.queryUnAnsweredIssuesId(repositoryName);
            List<Issue> issues = unAnsweredIssuesAggregates.getRespData().getIssues();
            logger.info("Pull unanswered issues question:\n-->\n");
            for (Issue issue : issues) {
                logger.info(issue.getTitle()+"\n");
            }

            if (issues == null || issues.isEmpty()) {
                logger.info("No unanswered issues were found in this search");
                return;
            }

            // 2. get ChatGPT answers
            Issue issue = issues.get(0);
            String answer = openAI.askForChatGPT(issue.getTitle().trim());

            // 3. post AI answers on GitHub issues
            boolean status = githubissuesApi.answer(repositoryName, issue.getNumber(), answer);
            logger.info("Pull ChatGPT answer: \n-->\nissue number: {}\nissue title: {}\nissue answer(AI): {}\nanswer status: {}\n",
                    issue.getNumber(), issue.getTitle(), answer, status);

        } catch (Exception e) {
            logger.error("AI answer question exception!", e);
        }
    }
}
