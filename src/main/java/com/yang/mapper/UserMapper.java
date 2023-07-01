package com.yang.mapper;

import com.yang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 增加一个用户
     * @param
     * @return
     */
    int addUser(String userid,String perm);

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
