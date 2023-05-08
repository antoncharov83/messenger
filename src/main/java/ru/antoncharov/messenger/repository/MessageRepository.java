package ru.antoncharov.messenger.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.model.Message;
import ru.antoncharov.messenger.model.User;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<Message, String> {

    Flux<Message> findAllByRecipientsContaining(User recipient);

    Mono<Message> findFirstByRecipientsContainingAndAndSender(User recipient, User sender);
}
