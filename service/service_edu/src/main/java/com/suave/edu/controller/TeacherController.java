package com.suave.edu.controller;


import com.suave.edu.entity.Teacher;
import com.suave.edu.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Suave
 * @since 2021-01-24
 */
@Api(tags = {"讲师管理"})
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("findAll")
    public List<Teacher> findAllTeacher() {
        return teacherService.list();
    }

    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable("id") String id) {
        return teacherService.removeById(id);
    }
}

