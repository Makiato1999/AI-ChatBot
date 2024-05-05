package com.chatbot.assitant.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ApiTest {
    private static final String REPO_URL = "https://api.github.com/repos/Makiato1999/ChatBot-api/issues";
    @Test
    public void fetchQuestion() {
        Properties config = new Properties();
        String TOKEN = "";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                config.load(inputStream);
                TOKEN = config.getProperty("github.token");
            } else {
                throw new FileNotFoundException("Sorry, unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create a HttpGet request
            HttpGet request = new HttpGet(REPO_URL);
            request.setHeader("Authorization", "token " + TOKEN);
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
    public void answerQuestion() {
        Properties config = new Properties();
        String TOKEN = "";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                config.load(inputStream);
                TOKEN = config.getProperty("github.token");
            } else {
                throw new FileNotFoundException("Sorry, unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String commentUrl = REPO_URL + "/" + "1" + "/comments";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(commentUrl);
            post.setHeader("Authorization", "token " + TOKEN);
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
}


