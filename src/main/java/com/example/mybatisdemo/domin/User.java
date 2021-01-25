package com.example.mybatisdemo.domin;

public class User {
    public String age;
    public String sex;
    public String username;
    public String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
