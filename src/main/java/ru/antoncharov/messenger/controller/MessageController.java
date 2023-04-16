package ru.antoncharov.messenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.antoncharov.messenger.model.Message;
import ru.antoncharov.messenger.service.MessageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{id}")
    public Flux<Message> getMessagesByRecipientId(String id) {
        return messageService.findAllByRecipientId(id);
    }
}
