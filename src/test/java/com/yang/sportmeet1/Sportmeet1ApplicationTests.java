package com.yang.sportmeet1;


import com.yang.mapper.*;
import com.yang.pojo.*;
import com.yang.service.AthleteService;
import com.yang.service.Impl.GradeServiceImpl;
import com.yang.service.Impl.GroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class Sportmeet1ApplicationTests {
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GradeServiceImpl gradeService;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AthleteMapper athleteMapper;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    GradeMapper gradeMapper;

    @Autowired
    AthleteService athleteService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GroupServiceImpl groupService;



    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        // Connection connection = dataSource.getConnection();
        // System.out.println(connection);
        // List<Group> groups = groupMapper.queryGroupList();
        // for (Group group:groups){
        //     System.out.println(group);
        // }
        // Group group = groupMapper.queryGroupById(1);
        // System.out.println(group);
        // groupMapper.addGroup(new Group(null,"杨航","搬砖学院"));
        // groupMapper.updateGroup(new Group(15,"洋洋","哈哈学院"));
        // groupMapper.deleteGroup(15);
        // List<Teacher> teachers = teacherMapper.queryTeacherList();
        // for (Teacher teacher : teachers) {
        //     System.out.println(teacher);
        // }
        // System.out.println(teacherMapper.queryTeacherById(220001));
        // teacherMapper.addTeacher(new Teacher(22,"阳台",1,"sadasda@ad.net","123123",""));
        // teacherMapper.updateTeacher(new Teacher(22,"阳台1",0,"sadasda@ad.net","123123",""));
        // teacherMapper.deleteTeacher(22);
        // List<Athlete> athletes = athleteMapper.queryAthleteList(1,5,"");
        // for (Athlete athlete : athletes) {
        //     System.out.println(athlete);
        // }
        // System.out.println(athleteMapper.queryAthleteById(1841302066));
        // athleteMapper.addAthlete(new Athlete(1841,new Group(1," "," ")
        //                            ,"杨一",18,1,"123123",""));

        // athleteMapper.updateAthlete(new Athlete(1841,new Group(2," "," ")
        //         ,"杨二二",18,1,"123123",""));
        // athleteMapper.deleteAthlete(1841);
        // List<Project> projects = projectMapper.queryProjectList();
        // for (Project project : projects) {
        //     System.out.println(project);
        // }
        // System.out.println(projectMapper.queryProjectById(1));
        // projectMapper.addProject(new Project(null,new Teacher(220001," ",0,null,null,null),
        //                          "跳马",new Date(),"马场"));

        // projectMapper.updateProject(new Project(5,new Teacher(220002," ",0,null,null,null),
        //         "跳马2",new Date(),"马场2"));
        // projectMapper.deleteProject(5);
        // List<Grade> grades = gradeMapper.queryGradeList();
        // for (Grade grade : grades) {
        //     System.out.println(grade);
        // }
        // List<Grade> grades = gradeMapper.queryGradeById(null,null,null,220001);
        // for (Grade grade : grades) {
        //     System.out.println(grade);
        // }
        // gradeMapper.addGrade(1841302066,3,20);
        // gradeMapper.updateGrade(1841302066,3,66);
        // gradeMapper.deleteGrade(1841302066,3);
        // List<Athlete> athletes = athleteService.queryAthleteList();
        // for (Athlete athlete : athletes) {
        //     System.out.println(athlete);
        // }
        // Athlete athlete = new Athlete(1841302069,
        //         new Group(2, null, null),
        //         "小杨", 18, 1, "123123", "perm:ath");
        // athleteMapper.addAthlete(athlete);
        // userMapper.addUser(UserUtils.ObjectForUser(athlete));
        // User user = new User();
        // userService.addUser(user,athlete);

        // List<Group> groups = groupService.queryGroupList();
        // System.out.println(groups);
        // Athlete athlete = new Athlete("1841302066",new Group(),null,null,null,"2222",null,null);
        // athleteService.updateAthlete(athlete);
        // List<Athlete> athletes = athleteMapper.queryStatusAthletes(1);
        // for (Athlete athlete : athletes) {
        //     System.out.println(athlete);
        // }
        // List<Grade> grades = gradeMapper.queryGradeList();
        // for (Grade grade : grades) {
        //     System.out.println(grade);
        // }

        // List<Grade> grades = gradeMapper.queryStatusAthlete(0,10);
        // for (Grade grade : grades) {
        //     System.out.println(grade);
        // }
        // athleteService.queryStatusAthletes(0,1,20);
       //  String grade = "66";
       // gradeService.updateGrade("1841302014","9",Float.parseFloat(grade));
       //  List<Grade> grades = gradeService.queryGradeById("1841302066", null);
       //  for (Grade grade : grades) {
       //      System.out.println(grade);
       //  }
        gradeService.addGrade("1841302066","7");
        gradeService.updateGrade("1841302066","7",-1,"1");
    }

}
