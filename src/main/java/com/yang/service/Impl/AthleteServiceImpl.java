package com.yang.service.Impl;

import com.yang.mapper.AthleteMapper;
import com.yang.pojo.Athlete;
import com.yang.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class AthleteServiceImpl implements AthleteService {

    private  AthleteMapper athleteMapper;

    @Autowired
    public AthleteServiceImpl(AthleteMapper athleteMapper) {
        this.athleteMapper = athleteMapper;
    }
    @Override
    public int addAthlete(Athlete athlete) {
        return athleteMapper.addAthlete(athlete);
    }

    @Override
    public int deleteAthlete(String anum) {
        return athleteMapper.deleteAthlete(anum);
    }

    @Override
    public int updateAthlete(Athlete athlete) {
        return athleteMapper.updateAthlete(athlete);
    }

    @Override
    public Athlete queryAthleteById(String anum) {
        return athleteMapper.queryAthleteById(anum);
    }

    @Override
    public List<Athlete> queryAthleteList(Integer pageNum,Integer pageSize,String aname) {
        pageNum = (pageNum-1) * pageSize;
        aname = "%"+aname+"%";
        return athleteMapper.queryAthleteList( pageNum,pageSize, aname);
    }

    @Override
    public Long getTotal(Integer status) {
        return athleteMapper.getTotal(status);
    }

    @Override
    public List<Athlete> queryStatusAthletes(Integer status, Integer pageNum , Integer pageSize) {
        pageNum = (pageNum-1) * pageSize;
        return athleteMapper.queryStatusAthletes(status,pageNum,pageSize);
    }


}
