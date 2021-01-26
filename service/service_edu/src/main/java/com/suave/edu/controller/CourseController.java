package com.suave.edu.controller;


import com.suave.common.result.R;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.vo.CourseInfoVO;
import com.suave.edu.entity.vo.CoursePublishVO;
import com.suave.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加课程基本信息
     *
     * @param courseInfoVo
     * @return
     */
    @ApiOperation(value = "")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVO courseInfoVo) {
        //返回添加之后课程id，为了第二个页面添加课程大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    /**
     * 根据课程id查询课程基本信息 回显
     *
     * @param courseId
     * @return
     */
    @ApiOperation(value = "根据课程id查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVO courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     * @return
     */
    @ApiOperation(value = "修改课程信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVO courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "确认课程信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVO coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }

    /**
     * 课程最终发布 修改课程表中status状态
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "课程最终发布")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }


}

