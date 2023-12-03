package net.croz.owasp.badexample.service.impl;

import net.croz.owasp.badexample.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl implements MessagingService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MessagingServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sendMessage(String destination, Object message) {
        simpMessagingTemplate.convertAndSend(destination, message);
    }

}
