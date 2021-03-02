package com.suave.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suave.common.result.CommonResult;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.Teacher;
import com.suave.edu.service.CourseService;
import com.suave.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Suave
 * @date 2021/1/29 11:41 上午
 */
@RestController
@RequestMapping("/front/edu/index")
public class IndexFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询前八条热门课程，前四名热名讲师
     * 排序规则之后可以根据需求进行修改
     *
     * @return
     */
    @GetMapping("hot")
    public CommonResult index() {

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<Course> courseList = courseService.list(wrapper);


        QueryWrapper<Teacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<Teacher> teacherList = teacherService.list(wrapperTeacher);

        return CommonResult.ok().data("courseList", courseList).data("teacherList", teacherList);
    }


}
