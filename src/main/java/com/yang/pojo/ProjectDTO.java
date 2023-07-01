package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hang yang
 * @create 2022-04-05 15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
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
    /**
     运动员编号
     */
    private String anum;
    /**
     运动员姓名
     */
    private String aname;
    /**
     * 状态
     */
    private String statu;
    /**
     * 成绩
     */
    private float grade;
}
