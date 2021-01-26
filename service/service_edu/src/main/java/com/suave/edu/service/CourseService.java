package com.suave.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.vo.CourseInfoVO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
public interface CourseService extends IService<Course> {

    /**
     * 添加课程基本信息
     *
     * @param courseInfoVO
     */
    void saveCourseInfo(CourseInfoVO courseInfoVO);
}
