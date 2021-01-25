package com.example.mybatisdemo.service;

import com.example.mybatisdemo.domin.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUser();
    User getUserById(int id);
    void insertUser(User user);
    void deleteUserById(int id);
    void updateUserByUsername(User user);
    User getUserByUsername(String username);
}
