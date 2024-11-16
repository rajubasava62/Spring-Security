package com.security.spring_security_demo.repository;

import com.security.spring_security_demo.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users,Integer> {
    public Users findByUsername(String username);
}
