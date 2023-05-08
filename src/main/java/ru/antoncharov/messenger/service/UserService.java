package ru.antoncharov.messenger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.dto.UserDto;
import ru.antoncharov.messenger.model.Role;
import ru.antoncharov.messenger.model.User;
import ru.antoncharov.messenger.model.UserRole;
import ru.antoncharov.messenger.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<UserDto> register(String username, String password) {
        var user = User.builder()
                .username(username)
                .password(password)
                .userRoles(Set.of(UserRole.builder()
                        .role(Role.builder()
                                .name("CHAT_USER")
                                .build())
                        .build()))
                .build();

        return userRepository.save(user).map(u -> new UserDto(u.getUsername()));
    }
}
