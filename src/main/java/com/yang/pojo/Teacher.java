package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 教师实体类
 * @author hang yang
 * @create 2022-01-05 20:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Teacher {
    /**
     * 教师编号
     */
    private String tnum;
    /**
     * 教师姓名
     */
    private String tname;
    /**
     * 教师性别
     */
    private Integer tsex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 权限
     */
    private String perm;
}
