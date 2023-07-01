package com.yang.mapper;

import com.yang.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper {
    /**
     * 增加一个学生比赛成绩
     * @param anum 运动员编号
     * @param pnum 比赛项目编号
     * @return
     */
    int addGrade(String anum,String pnum);

    /**
     * 根据比赛项目编号和运动员编号删除比赛成绩
     * @param pnum
     * @param anum
     * @return
     */
    int deleteGrade(String anum,String pnum);

    /**
     * 更新运动员比赛成绩
     * @param anum 运动员编号
     * @param pnum 比赛项目编号
     * @param grade 比赛成绩
     * @return
     */
    int updateGrade(String anum,String pnum,float grade,String statu);

    /**
     * 根据比赛项目编号或者运动员编号、系团队编号、教师编号查询成绩
     *
     * @param anum 运动员编号
     * @param pnum 比赛项目编号
     * @return
     */
    List<Grade> queryGradeById(String anum,String pnum);

    /**
     * 查询全部的运动员比赛成绩信息
     * @return
     */
    List<Grade> queryGradeList();

    /**
     * 查询运动员状态 0 未报名 1已报名未审核 2审核成功
     */
    List<Grade> queryStatusAthlete(Integer pageNum,Integer pageSize,String statu);

    /**
     * 统计条数
     */
    Long getTotal(String statu);
}
