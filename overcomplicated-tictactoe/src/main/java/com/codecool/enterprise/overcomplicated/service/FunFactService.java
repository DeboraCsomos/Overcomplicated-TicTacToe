package com.codecool.enterprise.overcomplicated.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FunFactService {

    public String getFunFact() {
        final String URI = "http://localhost:60001/new_fun_fact";
        RestTemplate template = new RestTemplate();
        String result;
        try {
            result = template.getForObject(URI, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            result = "&quot;Chuck Norris knows the last digit of pi.&quot;";
        }
        return result;
    }
}
