package com.application.controller;

import com.application.data.model.User;
import com.application.dto.LoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @PostMapping(path = "/login")
    public String login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto.toString());
        return "Login successful";
    }

    @PostMapping(path = "/add")
    public String login(@RequestBody User user) {
        return "Login successful";
    }
}
