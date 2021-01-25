package com.suave.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suave.common.result.R;
import com.suave.edu.entity.Teacher;
import com.suave.edu.entity.vo.TeacherQuery;
import com.suave.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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

    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        return R.ok().data("items", teacherService.list());
    }

    @ApiOperation("根据Id删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable("id") String id
    ) {
        return teacherService.removeById(id) ? R.ok() : R.error();
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable("current") Integer current,
            @ApiParam(name = "limit", value = "每页数量", required = true)
            @PathVariable("limit") Integer limit
    ) {
        Page<Teacher> pageTeacher = new Page<>(current, limit);
        teacherService.page(pageTeacher);
        return R.ok().data("pages", pageTeacher.getPages()).data("total", pageTeacher.getTotal()).data("rows", pageTeacher.getRecords());
    }

    @ApiOperation(value = "分页查询带条件")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {  //@RequestBody(required = false)参数值可以为空
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current, limit);

        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level.toString())) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);
        //总记录数
        long total = pageTeacher.getTotal();
        //数据list集合
        List<Teacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher) ? R.ok() : R.error();
    }

    @ApiOperation(value = "根据讲师id进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @ApiOperation(value = "讲师修改")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateById(teacher) ? R.ok() : R.error();
    }
}


