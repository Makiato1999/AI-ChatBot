package com.chatbot.assitant.test;

import com.alibaba.fastjson.JSON;
import com.chatbot.assitant.api.domain.githubissues.model.res.AnswerRes;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ApiTest {
    private static final String REPO_URL = "https://api.github.com/repos/Makiato1999/ChatBot-api/issues";
    private static final String chatGPT_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${ChatBot-api.githubToken}")
    private String githubToken;

    @Test
    public void test_fetchQuestion() {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create a HttpGet request
            HttpGet request = new HttpGet(REPO_URL);
            request.setHeader("Authorization", "token " + githubToken);
            request.setHeader("Accept", "application/vnd.github.v3+json");

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);

                // Process the JSON response
                if (rootNode.isArray()) {
                    for (JsonNode node : rootNode) {
                        System.out.println("Issue: #" + node.get("number").asText() +
                                " - " + node.get("title").asText());
                    }
                    System.out.println(jsonResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_answerQuestion() {
        String commentUrl = REPO_URL + "/" + "1" + "/comments";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(commentUrl);
            post.setHeader("Authorization", "token " + githubToken);
            post.setHeader("Accept", "application/vnd.github.v3+json");
            post.setHeader("Content-Type", "application/json");

            String json = "{\"body\": \"" + "I don't know!" + "\"}";
            post.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                    String jsonResponse = EntityUtils.toString(response.getEntity());
                    System.out.println(jsonResponse);
                } else {
                    System.out.println(response.getStatusLine().getStatusCode());
                }
                //System.out.println("Response: " + EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Value("${ChatBot-api.openAIKey}")
    private String openAIKey;
    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(chatGPT_URL);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+openAIKey);

        String paramJson = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [" +
                "{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"}," +
                "{\"role\": \"user\", \"content\": \"Briefly explain in a few words what is microservice architecture?\"}]}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode()  == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            System.out.println(jsonStr);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}


