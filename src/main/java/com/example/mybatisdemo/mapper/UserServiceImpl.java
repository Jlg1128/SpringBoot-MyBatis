package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.dao.UserDao;
import com.example.mybatisdemo.domin.User;
import com.example.mybatisdemo.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void insertUser(User user) {
         userDao.insertUser(user);
    }

    @Override
    public User getUserById(int id) {
        User user = userDao.getUserById(id);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        return user;
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
        System.out.println("删除res");
    }

    @Override
    public void updateUserByUsername(User user) {
        userDao.updateUserByUsername(user);
        System.out.println("更新res");
    }
}
