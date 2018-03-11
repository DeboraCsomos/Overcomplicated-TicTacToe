package com.codecool.enterprise.overcomplicated.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class AvatarService {

    public String getAvatarURI(String name) {
        String result;
        try {
            final URI URI = new URI("http://localhost:60004/new_avatar");
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            RequestEntity request = new RequestEntity<>(name, headers, HttpMethod.POST, URI);
            result = template.exchange(request, String.class).getBody();
        } catch (URISyntaxException | RestClientException e) {
            e.printStackTrace();
            result = "https://robohash.org/codecool/?set=set4";
        }
        return result;
    }

}
