package ru.antoncharov.messenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.antoncharov.messenger.dto.RegisterUserDto;
import ru.antoncharov.messenger.dto.UserDto;
import ru.antoncharov.messenger.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public Mono<UserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        return userService.register(registerUserDto.username(), registerUserDto.password());
    }
}
