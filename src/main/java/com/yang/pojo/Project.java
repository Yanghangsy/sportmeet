package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 项目表
 * @author hang yang
 * @create 2022-01-04 20:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    /**
     * 项目编号
     */
    private String pnum;
    /**
     * 教师
     */
    private Teacher teacher;
    /**
     *项目名称
     */
    private String pname;
    /**
     *比赛时间
     */
    private String ptime;
    /**
     *比赛地点
     */
    private String space;
    /**
     * 比赛名额
     */
    private Integer count;
}
