package ru.antoncharov.messenger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
        var recipient = userRepository.findById(id);

        return messageRepository.findAllByRecipientsContaining(recipient);
    }

    public void saveMessage(String fromId, List<String> toIds, String content) {
        var from = userRepository.findById(fromId);
        from.doOnSuccess(user -> {
            var recipients = userRepository.findAllById(toIds);
            recipients.doOnEach(recipient -> {
                var message = messageRepository.findFirstByRecipientsContainingAndAndSender(recipient.get(), user);
                message.doOnSuccess(foundMsg -> {
                    foundMsg.getRecipients().add(recipient.get());
                    messageRepository.save(foundMsg);
                }).switchIfEmpty(messageRepository.save(new Message(UUID.randomUUID().toString(), user, List.of(recipient.get()), content)));
            });
        });

    }
}
