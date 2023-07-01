package com.yang.service.Impl;

import com.yang.mapper.ProjectMapper;
import com.yang.pojo.Project;
import com.yang.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;
    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public int addProject(Project project) {
        return projectMapper.addProject(project);
    }

    @Override
    public int deleteProject(String pnum) {
        return projectMapper.deleteProject(pnum);
    }

    @Override
    public int updateProject(Project project) {
        return projectMapper.updateProject(project);
    }

    @Override
    public Project queryProjectById(String pnum) {
        return projectMapper.queryProjectById(pnum);
    }

    @Override
    public List<Project> queryProjectList(Integer pageNum,Integer pageSize,String pname) {
        pageNum = (pageNum-1) * pageSize;
        pname = "%"+pname+"%";
        return projectMapper.queryProjectList(pageNum,pageSize,pname);
    }
    @Override
   public Long getTotal(){
       return projectMapper.getTotal();
    }
}
