package com.codecool.enterprise.overcomplicated.service;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    public ArrayList<String> moveAI(ArrayList<String> board) {
        Gson gson = new Gson();
        String response;
        final String URI = "http://localhost:60002/move";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        Map<String, Object> map = new HashMap<>();
        map.put("board", board);
        HttpEntity<Map> request = new HttpEntity<>(map, headers);
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            response = template.exchange(URI, HttpMethod.POST, request, String.class).getBody();
            System.out.println(response);
        } catch (RestClientException e) {
            e.printStackTrace();
            response = null;
        }
        return gson.fromJson(response,ArrayList.class);
    }
}
