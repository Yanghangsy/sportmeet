package com.yang.mapper;

import com.yang.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hang yang
 * @create 2022-01-05 20:23
 */
@Mapper
@Repository
public interface TeacherMapper {
    /**
     * @description: 增加一个教师
     * @param  teacher
     * @return int
     *
     */
    int addTeacher(Teacher teacher);

    /***
     * @description: 更加id删除教师
     * @param  tnum
     * @return int
     *
     */
    int deleteTeacher(String tnum);

    /**
     * @description: 更新教师信息
     * @param  teacher
     * @return int
     *
     */
    int updateTeacher(Teacher teacher);

    /**
     * @description: 根据id查询教师
     * @param  tnum
     * @return com.yang.pojo.Teacher
     *
     */
    Teacher queryTeacherById(String tnum);

    /**
     * @description: 查询全部的教师
     * @param
     * @return java.util.List<com.yang.pojo.Teacher>
     *
     */
    List<Teacher> queryTeacherList(Integer pageNum,Integer pageSize,String tname);

    /**
     * 获取总条数
     * @return
     */
    Long getTotal();
}
