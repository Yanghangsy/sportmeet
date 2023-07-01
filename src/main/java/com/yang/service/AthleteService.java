package com.yang.service;

import com.yang.pojo.Athlete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AthleteService {
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

    List<Athlete> queryStatusAthletes(Integer status, Integer pageNum , Integer pageSize);
}
