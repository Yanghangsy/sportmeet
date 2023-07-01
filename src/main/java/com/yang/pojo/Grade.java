package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 记录成绩
 * @author hang yang
 * @create 2022-01-04 20:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    /**
     * 项目
     */
    private String pnum;
    /**
     *运动员
     */
    private String anum;
    /**
     *成绩
     */
    private float grade;
    /**
     * 状态
     */
    private String statu;
}
