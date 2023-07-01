package com.yang.mapper;

import com.yang.pojo.Athlete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hang yang
 * @create 2022-01-05 20:56
 */
@Mapper
@Repository
public interface AthleteMapper {

    /**
     * @description: 增加一个运动员
     * @Param() [athlete]
     * @return int
     */
    int addAthlete(Athlete athlete);

    /***
     * @description:更加id删除一个运动员
     * @Param() [anum]
     * @return int
     *
     */
    int deleteAthlete(String anum);
    /***
     * @description:更新运动员信息
     * @Param() [athlete]
     * @return int
     *
     */
    int updateAthlete(Athlete athlete);

    /**
     * @description: 根据id查询运动员信息
     * @Param() [anum]
     * @return com.yang.pojo.Athlete
     *
     */
    Athlete queryAthleteById(String anum);
    /**
     * @description:查询全部运动员信息
     * @Param() []
     * @return java.util.List<com.yang.pojo.Athlete>
     *
     */
    List<Athlete> queryAthleteList(Integer pageNum,Integer pageSize,String aname);

    Long getTotal(Integer status);

    /**
     * 根据状态查询运动员报名情况 0 未报名 1已报名未审核 2审核成功
     */
    List<Athlete> queryStatusAthletes(Integer status, Integer pageNum , Integer pageSize);



}
