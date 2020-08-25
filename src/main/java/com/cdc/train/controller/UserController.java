package com.cdc.train.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/train")
public class UserController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

}
