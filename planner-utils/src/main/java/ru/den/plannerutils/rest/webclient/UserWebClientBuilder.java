package ru.den.plannerutils.rest.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.den.planner.entity.User;

@Component
public class UserWebClientBuilder {

    //    @Value("${user.resttemplate.url}")
    private final static String baseURL = "http://localhost:8765/planner-users/users/";

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
}

