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
     * @return
     */
    String saveCourseInfo(CourseInfoVO courseInfoVO);


    /**
     * 根据课程id查询课程基本信息 回显
     *
     * @param courseId
     * @return
     */
    CourseInfoVO getCourseInfo(String courseId);

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVO courseInfoVo);
}
