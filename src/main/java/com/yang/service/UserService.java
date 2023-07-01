package com.yang.service;

import com.yang.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    /**
     * 增加一个用户
     * @param
     * @return
     */
    int addUserTeacher(String userid);

    /**
     * 根据用户id删除一个用户
     * @param userid
     * @return
     */
    int deleteUser(String userid);

    /**
     * 更新一个用户信息与运动员信息
     * @param user
     * @return
     */
    int updateUserAndAthlete(User user);

    /**
     * 更新一个用户信息与教师信息
     * @param user
     * @return
     */
    int updateUserAndTeacher(User user);

    /**
     * 根据一个用户id查询用户信息
     * @param userid
     * @return
     */
    User queryUserById(String userid);

    /**
     * 查询全部用户信息
     * @return
     */
    List<User> queryUserList();
}
