package com.yang.controller.teacher;

import com.yang.common.Result;
import com.yang.pojo.*;
import com.yang.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hang yang
 * @create 2022-04-03 12:40
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProjectServiceImpl projectService;
    @Autowired
    AthleteServiceImpl athleteService;
    @Autowired
    GradeServiceImpl gradeService;
    @GetMapping("/{userid}")
    public Result getTeacher(@PathVariable String userid){
        return Result.success(teacherService.queryTeacherById(userid));
    }
    @PostMapping("/password")
    public Result password(@RequestBody UserPassWordDTO userPassWordDTO){
        Teacher teacher = teacherService.queryTeacherById(userPassWordDTO.getUserid());
        User user = userService.queryUserById(userPassWordDTO.getUserid());
        String password = userPassWordDTO.getPassword();
        if (teacher.getPwd().equals(password)&&user.getPwd().equals(password)){
           Teacher teacher1 = new Teacher();
            teacher1.setTnum(userPassWordDTO.getUserid());
            teacher1.setPwd(userPassWordDTO.getNewPassword());
            teacherService.updateTeacher(teacher1);
            User user1 = new User();
            user1.setUserid(userPassWordDTO.getUserid()) ;
            user1.setPwd(userPassWordDTO.getNewPassword());
            userService.updateUserAndAthlete(user1);
            return Result.success();
        }else {
            return Result.error("401","输入密码不正确");
        }

    }

    @GetMapping("/getProjects")
    public Result getProject(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam(defaultValue = "") String pname){
        Map<String, Object> map = new HashMap<>();
        Long total = projectService.getTotal();
        map.put("total",total);
        map.put("data", projectService.queryProjectList( pageNum, pageSize, pname));
        map.put("teachers",teacherService.queryTeacherList(1,100,""));
        return Result.success(map);
    }
    @DeleteMapping("/{pnum}")
    public Result deleteProject(@PathVariable String pnum){
        return Result.success(projectService.deleteProject(pnum));
    }

    @RequestMapping("/update")
    public  Result updateProject(@RequestBody Project project){
        return Result.success(projectService.updateProject(project));
    }
    @RequestMapping("/add")
    public  Result addProject(@RequestBody Project project){
        return Result.success(projectService.addProject(project));
    }
    @RequestMapping("/getSign")
    public Result getSign(@RequestParam Integer pageNum ,
                         @RequestParam Integer pageSize){
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<ProjectDTO> list = new ArrayList<>();
        map.put("total",gradeService.getTotal("1"));
        List<Grade> grades = gradeService.queryStatusAthlete(pageNum, pageSize, "1");
        for (Grade grade : grades) {
            Project project = projectService.queryProjectById(grade.getPnum());
            Athlete athlete = athleteService.queryAthleteById(grade.getAnum());
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setPnum(project.getPnum());
            projectDTO.setTeacher(project.getTeacher());
            projectDTO.setPname(project.getPname());
            projectDTO.setPtime(project.getPtime());
            projectDTO.setSpace(project.getSpace());
            projectDTO.setAname(athlete.getAname());
            projectDTO.setAnum(athlete.getAnum());
            projectDTO.setStatu(grade.getStatu());
            list.add(projectDTO);
        }
        map.put("data",list);
        return Result.success(map);
    }
    @RequestMapping("/audit")
    public  Result audit(@RequestBody ProjectDTO projectDTO){
        gradeService.updateGrade(projectDTO.getAnum(),projectDTO.getPnum(),projectDTO.getGrade(),"2");
        return Result.success();
    }

    @RequestMapping("/getRecord")
    public Result getRecord(@RequestParam Integer pageNum ,
                          @RequestParam Integer pageSize){
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<ProjectDTO> list = new ArrayList<>();
        map.put("total",gradeService.getTotal("2"));
        List<Grade> grades = gradeService.queryStatusAthlete(pageNum, pageSize, "2");
        for (Grade grade : grades) {
            Project project = projectService.queryProjectById(grade.getPnum());
            Athlete athlete = athleteService.queryAthleteById(grade.getAnum());
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setPnum(project.getPnum());
            projectDTO.setTeacher(project.getTeacher());
            projectDTO.setPname(project.getPname());
            projectDTO.setPtime(project.getPtime());
            projectDTO.setSpace(project.getSpace());
            projectDTO.setAname(athlete.getAname());
            projectDTO.setAnum(athlete.getAnum());
            projectDTO.setStatu(grade.getStatu());
            projectDTO.setGrade(grade.getGrade());
            list.add(projectDTO);
        }
        map.put("data",list);
        return Result.success(map);
    }

    @RequestMapping("/recordSuccess")
    public Result recordSuccess(@RequestParam String anum,
                                @RequestParam String pnum,
                                @RequestParam String grade){
        gradeService.updateGrade(anum,pnum,Float.parseFloat(grade),null);
        return Result.success();
    }
}
