package com.yang.service;

import com.yang.pojo.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
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
