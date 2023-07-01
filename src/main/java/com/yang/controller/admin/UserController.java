package com.yang.controller.admin;
import com.yang.common.Result;
import com.yang.pojo.Athlete;
import com.yang.pojo.Group;
import com.yang.pojo.Teacher;
import com.yang.service.Impl.AthleteServiceImpl;
import com.yang.service.Impl.GroupServiceImpl;
import com.yang.service.Impl.TeacherServiceImpl;
import com.yang.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author hang yang
 * @create 2022-03-24 14:43
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    AthleteServiceImpl athleteService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    GroupServiceImpl groupService;
    //查询全部用户信息


    @GetMapping("/teacher/getAll")
    public Result getAllTeacher(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String tname){
        Long total = teacherService.getTotal();
        Map<String, Object> res = new HashMap();
        res.put("total",total);
        res.put("data",teacherService.queryTeacherList(pageNum,pageSize,tname));
       return  Result.success(res);
    }

    @PostMapping("/teacher/add")
    public Result addTeacher(@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return Result.success(userService.addUserTeacher( teacher.getTnum().toString()));
    }

    @DeleteMapping("/teacher/{tnum}")
    public Result delete(@PathVariable String tnum){
        return Result.success(teacherService.deleteTeacher(tnum));
    }

    @PostMapping("/teacher/update")
    public Result update( @RequestBody Teacher teacher){
        return Result.success(teacherService.updateTeacher(teacher));
    }

    @GetMapping("/{userid}")
    public  Result getUser(@PathVariable String userid){
        return Result.success(userService.queryUserById(userid));
    }

    @GetMapping("/athlete/getAll")
    public Result getAllAthlete(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String aname){
        Long total = athleteService.getTotal(null);
        Map<String, Object> res = new HashMap();
        res.put("total",total);
        res.put("data",athleteService.queryAthleteList(pageNum,pageSize,aname));
        res.put("groups",groupService.queryGroupList());
        return  Result.success(res);
    }

    @PostMapping("/athlete/add")
    public Result addAthlete(@RequestBody Athlete athlete){
        athleteService.addAthlete(athlete);
        return Result.success(userService.addUserAthlete(athlete.getAnum().toString()));
    }

    @DeleteMapping("/athlete/{anum}")
    public Result deleteAthlete(@PathVariable String anum){
        return Result.success(athleteService.deleteAthlete(anum));
    }

    @PostMapping("/athlete/update")
    public Result updateAthlete( @RequestBody Athlete athlete){
        return Result.success(athleteService.updateAthlete(athlete));
    }

}
