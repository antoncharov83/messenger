package ru.antoncharov.messenger.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.model.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findUserByUsername(String username);
}
