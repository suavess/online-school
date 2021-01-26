package com.suave.edu.controller;


import com.suave.common.result.R;
import com.suave.edu.entity.subject.SubjectDTO;
import com.suave.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Suave
 * @since 2021-01-25
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/edu/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 添加课程分类
     * 获取上传过来文件，把文件内容读取出来
     */
    @ApiOperation(value = "读取excel内容")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    /**
     * 课程分类列表功能
     * 树形结构显示
     */
    @ApiOperation(value = "课程分类列表")
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合泛型是一级分类  一级分类下包含二级分类
        List<SubjectDTO> list = subjectService.getAllSubjectList(null);
        return R.ok().data("list", list);
    }

}

