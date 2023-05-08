package ru.antoncharov.messenger.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.model.User;

import java.util.List;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    @Query("{username:'?0'}")
    Mono<User> findUserByUsername(String username);

    Flux<User> findAllByUsernameIn(List<String> usernames);

}

