package com.codecool.enterprise.overcomplicated.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ComicService {

    public Map<String, String> getComic() {
        final String URI = "http://localhost:60003/new_comic";
        RestTemplate template = new RestTemplate();
        String result;
        try {
            result = template.getForObject(URI, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            result = "{'uri': 'https://imgs.xkcd.com/comics/bad_code.png', 'alt': '', 'title': 'bad code'}";
        }
        return new Gson().fromJson(result, Map.class);
    }
}
