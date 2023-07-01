package com.yang.mapper;

import com.yang.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hang yang
 * @create 2022-01-06 21:21
 */
@Mapper
@Repository
public interface ProjectMapper {
    /***
     * @description:增加一个比赛项目
     * @Param() [project]
     * @return int
     *
     */
     int addProject(Project project);

     /***
      * @description:根据id删除一个比赛项目
      * @Param() [pnum]
      * @return int
      *
      */
     int deleteProject(String pnum);

     /***
      * @description:更新一个比赛项目信息
      * @Param() [project]
      * @return int
      *
      */
     int updateProject(Project project);

     /***
      * @description:根据id查找一个比赛项目信息
      * @Param() [pnum]
      * @return com.yang.pojo.Project
      *
      */
     Project queryProjectById(String pnum);

     /***
      * @description:查找全部的比赛项目信息
      * @Param() []
      * @return java.util.List<com.yang.pojo.Project>
      *
      */
     List<Project> queryProjectList(Integer pageNum,Integer pageSize,String pname);

     Long getTotal();
}
