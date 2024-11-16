package com.security.spring_security_demo.service;

import com.security.spring_security_demo.entity.Users;
import com.security.spring_security_demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

   private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String saveUser(Users user) {
      Users user1= userRepo.findByUsername(user.getUsername());
       if(user1!=null &&user1.getUsername().equals(user.getUsername())){
           return "user alredy exists";
       }
           user.setPassword(encoder.encode(user.getPassword()));
           System.out.println(user.getPassword());
            userRepo.save(user);
            return "user details saved successfully";
        }

    public List<Users> fetchAll() {
       return userRepo.findAll();
    }
}
