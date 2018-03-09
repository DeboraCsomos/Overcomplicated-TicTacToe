package com.codecool.enterprise.funfact.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class FunFactController {

    @RequestMapping(value = "/new_fun_fact", method = RequestMethod.GET)
    public String getFunFact() throws URISyntaxException {
        final URI URI = new URI("https://api.chucknorris.io/jokes/random");
        String response;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
        RequestEntity<String> entity = new RequestEntity<>(headers, HttpMethod.GET, URI);

        try {
            response = template.exchange(entity, String.class).getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            return "&quot;Chuck Norris knows the last digit of pi.&quot;";
        }
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        return "&quot;" + jsonObject.get("value").getAsString() + "&quot;";
    }
}
