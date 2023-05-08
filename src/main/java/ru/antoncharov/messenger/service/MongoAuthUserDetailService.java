package ru.antoncharov.messenger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.model.User;
import ru.antoncharov.messenger.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MongoAuthUserDetailService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<User> user = userRepository.findUserByUsername(username);
        return user.cast(UserDetails.class);
    }
}
