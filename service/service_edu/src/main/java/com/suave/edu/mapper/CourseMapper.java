package com.suave.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.vo.CoursePublishVO;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 根据课程id查询课程确认信息
     *
     * @param courseId
     * @return
     */
    CoursePublishVO getPublishCourseInfo(String courseId);
}
