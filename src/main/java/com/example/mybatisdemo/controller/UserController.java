package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.Responese.MyResponese;
import com.example.mybatisdemo.domin.User;
import com.example.mybatisdemo.service.UserService;
import com.example.mybatisdemo.vo.ResultVO;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.*;

@Validated
@Description("用户接口")
@RestController
@RequestMapping(value = "/api", produces = "application/json; charset=utf-8")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        System.out.println("userService.getUserList()" + userService.getAllUser());
        return userService.getAllUser();
    }

    @GetMapping("/getUser")
    public ResponseEntity<ResultVO> getUser(@RequestParam("id") @Valid @NotNull(message = "id 不能为空") int id) {
//        System.out.println("singleUser" + userService.getUserById(id));
        ResultVO resultV0 = MyResponese.success(userService.getUserById(id));
        return ResponseEntity.ok(resultV0);
    }

    @PostMapping(value = "/saveUser}", produces = "application/json; charset=utf-8")
    public ResultVO insertUser(@RequestBody User userInfo
    ) {
        System.out.println("user" + userInfo.age);
        System.out.println("user" + userInfo.username);
        User user = new User();
        user.setAge(userInfo.age);
        user.setUsername(userInfo.username);
        user.setSex(userInfo.sex);
        userService.insertUser(user);
        return MyResponese.success(null, "新增成功");
    }
    @PostMapping(value = "/deleteUser", produces = "application/json; charset=utf-8")
    public ResultVO deleteUser(@RequestBody Map<String, Integer> idMap
    ) {
        int id = idMap.get("id");
        if (String.valueOf(id) == null ) {
            return MyResponese.error("id不能为空");
        }
        try {
            User user = userService.getUserById(id);
            if (user instanceof User) {
                userService.deleteUserById(Integer.valueOf(id));
            } else {
                return MyResponese.success(null, "该用户不存在");
            }
        } catch (Exception e)
        {
            System.out.print(e.getStackTrace().toString());
            return MyResponese.error("执行异常");
        }
        return MyResponese.success(null, "删除成功");
    }
    @PostMapping(value = "/updateUser", produces = "application/json; charset=utf-8")
    public ResultVO updateUser(@RequestBody User user
    ) throws IllegalAccessException {
        for (Field str: user.getClass().getFields()) {
            str.setAccessible(true);
            System.out.println(str.getName());
            System.out.println(str.get(user));
        }
        try {
            if (userService.getUserByUsername(user.username) != null) {
                userService.updateUserByUsername(user);
            } else {
                return MyResponese.success(null, "用户不存在");
            }
        } catch (Exception e)
        {
            System.out.println(e.getStackTrace());
            return MyResponese.error("执行异常");
        }
        return MyResponese.success(null);
    }
}
