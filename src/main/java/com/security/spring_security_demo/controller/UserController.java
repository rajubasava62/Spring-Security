package com.security.spring_security_demo.controller;

import com.security.spring_security_demo.entity.Users;
import com.security.spring_security_demo.service.JwtService;
import com.security.spring_security_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/fetch")
    public List<Users> fetchAllUsers(){
        return userService.fetchAll();
    }

    @PostMapping ("/register")
    public String saveUser(@RequestBody Users user){
      return userService.saveUser(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else{
            return  "Login Failed";
        }
    }


}

