package com.springboot.einventura.model.service;


import com.springboot.einventura.model.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {

    List<User> findAll();

    Optional<User> findByEmail(String theEmail);

    Optional<User> findById(int theId);

    User save(User theUser);

    void deleteById(int theId);
}
