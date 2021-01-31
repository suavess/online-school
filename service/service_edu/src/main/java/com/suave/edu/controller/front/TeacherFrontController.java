package com.suave.edu.controller.front;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suave.common.result.R;
import com.suave.edu.entity.Teacher;
import com.suave.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Suave
 * @date 2021/1/31 10:38 下午
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherFrontController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(
            @PathVariable("page") @ApiParam(name = "page", value = "当前页码", required = true) Integer page,
            @PathVariable("limit") @ApiParam(name = "limit", value = "每页记录数", required = true) Integer limit) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        IPage<Teacher> data = teacherService.getTeacherFrontList(teacherPage);
        return R.ok().data("data", data);
    }

}
