package com.suave.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suave.common.result.CommonResult;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.Teacher;
import com.suave.edu.service.CourseService;
import com.suave.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Suave
 * @date 2021/1/31 10:38 下午
 */
@RestController
@RequestMapping("/front/edu/teacher")
public class TeacherFrontController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public CommonResult getTeacherFrontList(
            @PathVariable("page") @ApiParam(name = "page", value = "当前页码", required = true) Integer page,
            @PathVariable("limit") @ApiParam(name = "limit", value = "每页记录数", required = true) Integer limit) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        IPage<Teacher> data = teacherService.getTeacherFrontList(teacherPage);
        return CommonResult.ok().data("data", data);
    }

    /**
     * 查询老师的信息，以及所将的课程
     *
     * @param teacherId
     * @return
     */
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public CommonResult getTeacherFrontInfo(@PathVariable String teacherId) {
        // 1. 根据讲师id查询讲师的基本信息
        Teacher teacher = teacherService.getById(teacherId);

        // 2. 根据讲师id查询所讲的课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<Course> courseList = courseService.list(wrapper);

        return CommonResult.ok().data("teacher", teacher).data("courseList", courseList);
    }


}
