package com.yang.service.Impl;

import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int addUserTeacher(String userid) {
        String perm = "perm:tea";
       return userMapper.addUser(userid,perm);
    }
    public int addUserAthlete(String userid) {
        String perm = "perm:ath";
        return userMapper.addUser(userid,perm);
    }

    @Override
    public int deleteUser(String userid) {
        return userMapper.deleteUser(userid);
    }

    @Override
    public int updateUserAndAthlete(User user) {
        return userMapper.updateUserAndAthlete(user);
    }

    @Override
    public int updateUserAndTeacher(User user) {
        return userMapper.updateUserAndTeacher(user);
    }


    @Override
    public User queryUserById(String userid) {
        return userMapper.queryUserById(userid);
    }

    @Override
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }
}
