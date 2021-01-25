package com.example.mybatisdemo.dao;

import com.example.mybatisdemo.domin.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    List<User> getAllUser();
    User getUserById(int id);
    User getUserByUsername(String username);
    void insertUser(User user);
    void deleteUserById(int id);
    void updateUserByUsername(User user);
}
