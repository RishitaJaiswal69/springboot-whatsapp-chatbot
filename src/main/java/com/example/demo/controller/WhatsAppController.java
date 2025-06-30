
package com.example.demo.controller;

import com.example.demo.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    private final WhatsAppService whatsAppService;

    @Autowired
    public WhatsAppController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendWhatsAppMessage(
            @RequestParam String to,
            @RequestParam String message
    ) {
        // Log for debugging
        System.out.println("Sending message to: " + to);

        // Call your service
        String apiResponse = whatsAppService.sendMessage(to, message);

        // You can log the raw response if you like
        System.out.println("WhatsApp API Response: " + apiResponse);

        // Return clean success message to frontend
        return ResponseEntity.ok("Message sent successfully! Response: " + apiResponse);

    }
}
