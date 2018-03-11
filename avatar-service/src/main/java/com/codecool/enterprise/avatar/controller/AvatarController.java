package com.codecool.enterprise.avatar.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvatarController {

    @RequestMapping(value = "/new_avatar", method = RequestMethod.POST)
    public String getAvatar(@RequestBody String name) {
        return String.format("https://robohash.org/%s/?set=set4", name);
    }
}
