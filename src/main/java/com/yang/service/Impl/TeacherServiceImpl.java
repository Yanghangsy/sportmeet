package com.yang.service.Impl;

import com.yang.mapper.TeacherMapper;
import com.yang.pojo.Teacher;
import com.yang.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TeacherServiceImpl implements TeacherService {

    private TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String tnum) {
        return teacherMapper.deleteTeacher(tnum);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Teacher queryTeacherById(String tnum) {
        return teacherMapper.queryTeacherById(tnum);
    }

    @Override
    public List<Teacher> queryTeacherList(Integer pageNum,Integer pageSize,String tname) {
        pageNum = (pageNum-1) * pageSize;
        tname = "%"+tname+"%";
        return teacherMapper.queryTeacherList(pageNum, pageSize,tname);
    }
    /**
     * 获取总条数
     * @return
     */
   @Override
   public Long getTotal(){
        return teacherMapper.getTotal();
    };
}
