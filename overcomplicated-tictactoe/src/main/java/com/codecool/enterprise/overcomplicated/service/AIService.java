package com.codecool.enterprise.overcomplicated.service;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class AIService {

    public ArrayList<String> moveAI(ArrayList<String> board) throws URISyntaxException {
        Gson gson = new Gson();
        String response;
        final URI URI = new URI("http://localhost:60002/move");
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        RequestEntity request = new RequestEntity(board, HttpMethod.POST, URI);
        try {
            response = template.exchange(request, String.class).getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            response = null;
        }
        return gson.fromJson(response, ArrayList.class);
    }
}
