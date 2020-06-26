package com.milo.automation.atframework.controller;

import com.milo.automation.atframework.mapper.TeacherMapper;
import com.milo.automation.atframework.POJO.DO.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeacherController {

    @Autowired
    TeacherMapper teacherMapper;

    @RequestMapping("/getTeacherByid/{Tid}")
    public Teacher getTeacherById(@PathVariable String Tid){
        Teacher teacher =  teacherMapper.getTeacherById(Tid);
        return teacher;
    }

}
