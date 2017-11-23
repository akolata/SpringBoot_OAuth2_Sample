package com.company.controller;

import com.company.model.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/api/test")
    public String sayHello(){
        return "Hello OAuth2 user";
    }

    @GetMapping(value = "/api/user")
    public ResponseEntity<?> showUser(Principal principal){

        User authenticatedUser = userRepository.findOneByUsername(principal.getName());

        if(authenticatedUser == null){
            return new ResponseEntity<>("Are You authenticated ?", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
        }

    }
}
