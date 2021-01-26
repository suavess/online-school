package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.base.exception.MyException;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.CourseDescription;
import com.suave.edu.entity.vo.CourseInfoVO;
import com.suave.edu.mapper.CourseMapper;
import com.suave.edu.service.CourseDescriptionService;
import com.suave.edu.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    /**
     * 添加课程基本信息
     *
     * @param courseInfoVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCourseInfo(CourseInfoVO courseInfoVO) {
        // 1.向课程表添加课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVO, course);
        if (this.baseMapper.insert(course) <= 0) {
            throw new MyException(20001, "添加课程信息失败！");
        }

        // 2.向课程简介表添加课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoVO.getDescription());
        courseDescriptionService.save(courseDescription);
    }
}
