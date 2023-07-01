package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 运动员实体类
 * @author hang yang
 * @create 2022-01-04 20:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Athlete {
    /**
    运动员编号
     */
    private String anum;
    /**
     * 系团队
     */

    private Group group;
    /**
     * 姓名
     */

    private String aname;
    /**
     年龄
     */
    private Integer age;
    /**
     *  性别 1 男 0 女
     */
    private Integer sex;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 权限
     */
    private String perm;
    /**
     * 状态
     */
    private Integer status;
}
