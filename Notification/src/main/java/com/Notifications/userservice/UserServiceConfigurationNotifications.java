package com.Notifications.userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class UserServiceConfigurationNotifications {

    @Bean
    public RestTemplate userServiceRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8888/api"));
        restTemplate.setInterceptors(Collections.singletonList(new TokenIntereceptor()));

        return restTemplate;
    }

    private class TokenIntereceptor implements ClientHttpRequestInterceptor{

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            headers.add("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfQURNSU4ifQ.dEuh0NrmaqBXOV5RrlIfUkTcKhXUJK0lf4gc7uanyuTmiTOdSkPEsMfB7CPt1pGOYz7JyVilV3cTs6u4IQtc7Q");
            return execution.execute(request,body);
        }

    }

}
