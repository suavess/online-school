package com.suave.edu.controller;


import com.suave.common.result.R;
import com.suave.edu.entity.vo.CourseInfoVO;
import com.suave.edu.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/edu/course")
@Api(tags = "课程")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVO courseInfoVO) {
        courseService.saveCourseInfo(courseInfoVO);
        return R.ok();
    }

}

