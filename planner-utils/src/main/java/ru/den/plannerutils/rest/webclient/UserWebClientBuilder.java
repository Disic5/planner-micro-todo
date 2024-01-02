package ru.den.plannerutils.rest.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.den.planner.entity.User;

@Component
public class UserWebClientBuilder {

    //    @Value("${user.resttemplate.url}")
    private final static String baseURL = "http://localhost:8765/planner-users/users/";
    private final static String baseData = "http://localhost:8765/planner-todo/data/";

    public boolean userExists(Long userId) {

        try {
            User user = WebClient.create(baseURL)
                    .post()
                    .uri("id")
                    .bodyValue(userId)
                    .retrieve()
                    .bodyToFlux(User.class)
                    .blockFirst();
            if (user != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //проверка - существует ли пользователь асинхронно
    public Flux<User> userExistAsync(Long userId){
        Flux<User> fluxUser = WebClient
                .create(baseURL)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User.class);
        return fluxUser;
    }

    //вызывамаем контроллер TestDataController
    public Flux<Boolean> initUserData(Long userId) {
        return WebClient
                .create(baseData)
                .post()
                .uri("init")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(Boolean.class);
    }
}

