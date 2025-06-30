package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import com.example.demo.model.ChatMessage;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class WhatsAppService {

	@Value("${whatsapp.access-token}")
    private String accessToken;

    private static final String WHATSAPP_API_URL = "https://graph.facebook.com/v19.0/{phone_number_id}/messages";
    
    private static final String PHONE_NUMBER_ID = "PHONE_NUMBER_ID";

    private final RestTemplate restTemplate;
    private final MessageRepository messageRepository;

    @Autowired
    public WhatsAppService(MessageRepository messageRepository) {
        this.restTemplate = new RestTemplate();
        this.messageRepository = messageRepository;
    }

    public String sendMessage(String to, String messageText) {

        // Prepare HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("messaging_product", "whatsapp");
        payload.put("to", to);
        payload.put("type", "text");

        Map<String, String> textObj = new HashMap<>();
        textObj.put("body", messageText);

        payload.put("text", textObj);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

        // Make the API call
        ResponseEntity<String> response = restTemplate.exchange(
                WHATSAPP_API_URL.replace("{phone_number_id}", PHONE_NUMBER_ID),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        
        ChatMessage chatMessage = new ChatMessage(
                UUID.randomUUID().toString(), // generate unique ID
                to,
                messageText,
                System.currentTimeMillis()
        );

        messageRepository.saveMessage(chatMessage);

        return response.getBody();
    }
}
