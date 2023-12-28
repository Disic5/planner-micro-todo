package ru.den.plannerutils.resttemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.den.planner.entity.User;

@Component
public class UserRestBuilder {

//    @Value("${user.resttemplate.url}")
    private final static String baseURL = "http://localhost:8765/planner-users/users/";

    public boolean userExists(Long userId){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> request = new HttpEntity<>(userId);
        ResponseEntity<User> response = null;

        try {
            response = restTemplate.exchange(baseURL + "/id", HttpMethod.POST, request, User.class);
            if (response.getStatusCode() == HttpStatus.OK){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
