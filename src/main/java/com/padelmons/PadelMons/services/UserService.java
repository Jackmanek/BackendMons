package com.padelmons.PadelMons.services;

import com.padelmons.PadelMons.entities.User;
import com.padelmons.PadelMons.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers(){
        return userRepository.findAll();
    }
}
