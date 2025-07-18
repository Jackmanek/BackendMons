package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.entities.User;
import com.padelmons.PadelMons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> allUsers(){
        return userService.allUsers();
    }

}
