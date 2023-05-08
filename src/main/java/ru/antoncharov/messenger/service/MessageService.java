package ru.antoncharov.messenger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.model.Message;
import ru.antoncharov.messenger.repository.MessageRepository;
import ru.antoncharov.messenger.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;


    public Flux<Message> findAllByRecipientId(String id) {
        return userRepository.findById(id).flatMapMany(user -> messageRepository.findAllByRecipientsContaining(user));
    }

    public Mono<Message> deleteMessageId(String id) {
        return messageRepository.findById(id).flatMap(message -> messageRepository.delete(message).then(Mono.just(message)))
                .switchIfEmpty(Mono.empty());
    }

    public void saveMessage(Mono<String> sender, List<String> recipientsUsername, String content) {
        sender.subscribe(username -> {
            var from = userRepository.findUserByUsername(username);
            from.subscribe(user -> {
                var recipients = userRepository.findAllByUsernameIn(recipientsUsername);
                recipients.subscribe(recipient -> {
                    var message = messageRepository.findFirstByRecipientsContainingAndAndSender(recipient, user);
                    message.doOnSuccess(foundMsg -> {
                        foundMsg.getRecipients().add(recipient);
                        messageRepository.save(foundMsg);
                    }).switchIfEmpty(messageRepository.save(new Message(UUID.randomUUID().toString(), user, List.of(recipient), content)));
                });
            });
        });
    }
}
