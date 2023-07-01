package com.yang.controller.athlete;

import com.yang.common.Result;
import com.yang.pojo.*;
import com.yang.service.Impl.AthleteServiceImpl;
import com.yang.service.Impl.GradeServiceImpl;
import com.yang.service.Impl.ProjectServiceImpl;
import com.yang.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author hang yang
 * @create 2022-04-02 22:35
 */
@RestController
@RequestMapping("/athlete")
public class AthleteController {
    @Autowired
    AthleteServiceImpl athleteService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    GradeServiceImpl gradeService;
    @Autowired
    ProjectServiceImpl projectService;
    @GetMapping("/{userid}")
    public Result getAthlete(@PathVariable String userid){
        return Result.success(athleteService.queryAthleteById(userid));
    }
    @PostMapping("/password")
    public Result password(@RequestBody UserPassWordDTO userPassWordDTO){
        Athlete athlete = athleteService.queryAthleteById(userPassWordDTO.getUserid());
        User user = userService.queryUserById(userPassWordDTO.getUserid());
        String password = userPassWordDTO.getPassword();
        if (athlete.getPwd().equals(password)&&user.getPwd().equals(password)){
            Athlete athlete1 = new Athlete();
            athlete1.setAnum(userPassWordDTO.getUserid());
            athlete1.setPwd(userPassWordDTO.getNewPassword());
            athlete1.setGroup(new Group());
            athleteService.updateAthlete(athlete1);
            User user1 = new User();
            user1.setUserid(userPassWordDTO.getUserid()) ;
            user1.setPwd(userPassWordDTO.getNewPassword());
            userService.updateUserAndAthlete(user1);
            return Result.success();
        }else {
            return Result.error("401","输入密码不正确");
        }

    }

    @RequestMapping("/event")
    public Result event(@RequestParam String anum,
                        @RequestParam String pnum){

        //查找运动员报名项目
        Project project = projectService.queryProjectById(pnum);
        //更新运动员状态
        Athlete athlete = athleteService.queryAthleteById(anum);

        List<Grade> grades = gradeService.queryGradeById(anum, null);
        if (grades.size()>=3){
            return Result.error("401","报名项目过多");
        }
        if (project.getCount()<=0){
            return Result.error("401","项目名额不够");
        }
        if (gradeService.queryGradeById(anum,pnum).size()>0){
            return Result.error("401","您已经报名过该项目");
        }
        //增加运动员与项目之间关联
        gradeService.addGrade(anum,pnum);

        gradeService.updateGrade(anum,pnum,-1,"1");
        //项目名额减一，更新项目
        project.setCount(project.getCount()-1);
        projectService.updateProject(project);

        athlete.setStatus(1);
        athleteService.updateAthlete(athlete);


        return Result.success();
    }
    @GetMapping("/grade")
    public Result grade(@RequestParam String anum){
        System.out.println("@@@@@anum@"+anum);
        ArrayList<ProjectDTO> list = new ArrayList<>();
        HashMap<String, Object> res = new HashMap<>();
        //查询运动详细信息
        Athlete athlete = athleteService.queryAthleteById(anum);
        //查询运动员参加过的比赛项目
        List<Grade> grades = gradeService.queryGradeById(anum, null);
        for (Grade grade : grades) {
            Project project = projectService.queryProjectById(grade.getPnum());
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setAnum(athlete.getAnum());
            projectDTO.setAname(athlete.getAname());
            projectDTO.setPnum(grade.getPnum());
            projectDTO.setStatu(grade.getStatu());
            projectDTO.setPname(project.getPname());
            projectDTO.setTeacher(project.getTeacher());
            projectDTO.setPtime(project.getPtime());
            projectDTO.setSpace(project.getSpace());
            projectDTO.setGrade(grade.getGrade());
            System.out.println(projectDTO);
            list.add(projectDTO);
        }
        for (ProjectDTO pro : list) {
            System.out.println(pro);
        }
        res.put("total",10);
        res.put("size",list.size());
        res.put("data",list);
        return Result.success(res);
    }
}
