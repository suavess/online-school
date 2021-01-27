package com.suave.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suave.common.result.R;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.vo.CourseInfoVO;
import com.suave.edu.entity.vo.CoursePublishVO;
import com.suave.edu.entity.vo.CourseQuery;
import com.suave.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value = "添加课程基本信息")
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

    /**
     * 课程列表分页查询显示带条件
     *
     * @param current     当前页
     * @param limit       每页记录数
     * @param courseQuery
     * @return
     */
    @ApiOperation(value = "课程列表分页查询显示带条件")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                 @RequestBody(required = false) CourseQuery courseQuery) {
        //创建page对象
        Page<Course> pageCourse = new Page<>(current, limit);
        //构建条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        // mybatis学过 动态sql
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        courseService.page(pageCourse, wrapper);
        //总记录数
        long total = pageCourse.getTotal();
        //数据list集合
        List<Course> records = pageCourse.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    /**
     * 删除课程
     *
     * @param courseId
     * @return
     */
    @ApiOperation(value = "删除课程")
    @DeleteMapping("deleteCourseById/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }


}

