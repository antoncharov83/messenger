package ru.antoncharov.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.antoncharov.messenger.dto.RsaProperties;

@SpringBootApplication
//@EnableConfigurationProperties(RsaProperties.class)
public class MessengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessengerApplication.class, args);
    }

//    @Bean
//    public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrationRepo, ServerOAuth2AuthorizedClientRepository authorizedClientRepo) {
//        ServerOAuth2AuthorizedClientExchangeFilterFunction filter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepo, authorizedClientRepo);
//        return WebClient.builder()
//                .filter(filter)
//                .build();
//    }
}
