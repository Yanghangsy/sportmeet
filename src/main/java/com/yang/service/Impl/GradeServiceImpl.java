package com.yang.service.Impl;

import com.yang.mapper.GradeMapper;
import com.yang.pojo.Grade;
import com.yang.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GradeServiceImpl implements GradeService {

    private GradeMapper gradeMapper;

    @Autowired
    public GradeServiceImpl(GradeMapper gradeMapper) {
        this.gradeMapper = gradeMapper;
    }

    @Override
    public int addGrade(String anum,String pnum) {
        return gradeMapper.addGrade(anum, pnum);
    }

    @Override
    public int deleteGrade(String anum,String pnum) {
        return gradeMapper.deleteGrade(anum, pnum);
    }

    @Override
    public int updateGrade(String anum,String pnum, float grade,String statu) {
        return gradeMapper.updateGrade(anum, pnum, grade,statu);
    }

    @Override
    public List<Grade> queryGradeById(String anum,String pnum) {
        return gradeMapper.queryGradeById(anum, pnum);
    }

    @Override
    public List<Grade> queryGradeList() {
        return gradeMapper.queryGradeList();
    }

    @Override
    public List<Grade> queryStatusAthlete( Integer pageNum,Integer pageSize,String statu) {
        pageNum = (pageNum-1) * pageSize;
        return gradeMapper.queryStatusAthlete(pageNum,pageSize,statu);
    }

    @Override
    public Long getTotal(String statu) {
        return gradeMapper.getTotal(statu);
    }
}
