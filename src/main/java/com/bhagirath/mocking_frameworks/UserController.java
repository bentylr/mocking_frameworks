package com.bhagirath.mocking_frameworks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    private final UserService userService;
    private final UserLogic userLogic;

    @Autowired
    public UserController(UserService userService, UserLogic userLogic) {
        this.userService = userService;
        this.userLogic = userLogic;
    }

    @GetMapping(value = "/users")
    public User getUserById(@RequestParam(value = "userId") String id) {
        try {
            return userService.findUserById(id);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
