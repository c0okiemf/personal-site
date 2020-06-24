package com.site.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.app.AppApplication;
import com.site.app.model.Feedback;
import com.site.app.repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FeedbackController {

    @Value("${app.telegram.bot}")
    private String telegramBot;

    @Value("${app.telegram.chat-id}")
    private String chatId;

    private final FeedbackRepository repository;

    private static final Logger log = LoggerFactory.getLogger(AppApplication.class);

    FeedbackController(FeedbackRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/feedback")
    Feedback newFeedback(@RequestBody Feedback feedback) {
        Feedback result = repository.save(feedback);
        this.sendMessage(result);
        return result;
    }

    private void sendMessage(Feedback feedback) {
        final String uri =
                "https://api.telegram.org/" + telegramBot + "/sendMessage";
        Map<String, String> elements = new HashMap();
        elements.put("chat_id", chatId);
        elements.put("text",  "Name: " + feedback.getName() + "\n"
                + "Email: " + feedback.getEmail() + "\n"
                + "Message: " + feedback.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(elements);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String result = restTemplate.postForObject(uri, new HttpEntity<String>(json, headers), String.class);
            Map map = objectMapper.readValue(result, Map.class);
            if (!((boolean) map.get("ok"))) {
                throw new Exception("Can't send, id " + feedback.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
