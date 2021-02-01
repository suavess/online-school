package com.suave.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.CourseWebVO;
import com.suave.edu.entity.frontvo.CourseQueryVO;
import com.suave.edu.entity.vo.CourseInfoVO;
import com.suave.edu.entity.vo.CoursePublishVO;

import java.util.Map;

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

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id
     * @return
     */
    CoursePublishVO publishCourseInfo(String id);

    /**
     * 删除课程
     *
     * @param courseId
     */
    void removeCourse(String courseId);

    /**
     * 前台带条件的分页查询前台
     *
     * @param pageCourse
     * @param courseQueryVo
     * @return
     */
    Map<String, Object> getCourseFrontInfo(Page<Course> pageCourse, CourseQueryVO courseQueryVo);

    /**
     * 根据课程id查询课程信息，用于前端的展示页面
     *
     * @param courseId
     * @return
     */
    CourseWebVO getBaseCourseInfo(String courseId);

    /**
     * 更新课程浏览数
     *
     * @param courseId
     */
    void updatePageViewCount(String courseId);

}
