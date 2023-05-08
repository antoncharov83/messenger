package ru.antoncharov.messenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.dto.SaveMessageDto;
import ru.antoncharov.messenger.model.Message;
import ru.antoncharov.messenger.service.MessageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{id}")
    public Flux<Message> getMessagesByRecipientId(@PathVariable String id) {
        return messageService.findAllByRecipientId(id);
    }

    @PostMapping("/")
    public ResponseEntity saveMessage(@AuthenticationPrincipal Mono<OAuth2User> user, @RequestBody SaveMessageDto saveMessageDto) {
        messageService.saveMessage(user.map(OAuth2User::getName), saveMessageDto.recipients(), saveMessageDto.content());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public Mono<Message> deleteById(@PathVariable String id) {
        return messageService.deleteMessageId(id);
    }
}
