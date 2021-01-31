package com.suave.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.edu.entity.Teacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Suave
 * @since 2021-01-24
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 分页查询讲师
     *
     * @param teacherPage
     * @return
     */
    IPage<Teacher> getTeacherFrontList(Page<Teacher> teacherPage);
}
