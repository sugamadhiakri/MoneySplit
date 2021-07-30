package com.moneysplit.api.controller;

import java.util.List;

import com.moneysplit.api.model.User;
import com.moneysplit.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    List<User> getAll() {
        return userService.findAllUsers();
    }
}
