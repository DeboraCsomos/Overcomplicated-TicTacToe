package com.codecool.enterprise.comics.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class ComicsController {

    @RequestMapping(value = "/new_comic", method = RequestMethod.GET)
    public String getComic() throws URISyntaxException {
        Random rand = new Random();
        int randomNumber = rand.nextInt(1929) + 1;
        final URI URI = new URI(String.format("https://xkcd.com/%d/info.0.json", randomNumber));
        String response;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, URI);

        try {
            response = template.exchange(request, String.class).getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            return "https://imgs.xkcd.com/comics/bad_code.png";
        }
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("uri", jsonObject.get("img").getAsString());
        infoMap.put("alt", jsonObject.get("alt").getAsString());
        infoMap.put("title", jsonObject.get("safe_title").getAsString());

        return new Gson().toJson(infoMap);
    }
}
