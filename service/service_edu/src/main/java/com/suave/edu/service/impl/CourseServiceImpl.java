package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.base.exception.MyException;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.CourseDescription;
import com.suave.edu.entity.vo.CourseInfoVO;
import com.suave.edu.mapper.CourseMapper;
import com.suave.edu.service.ChapterService;
import com.suave.edu.service.CourseDescriptionService;
import com.suave.edu.service.CourseService;
import com.suave.edu.service.VideoService;
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

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    /**
     * 添加课程基本信息
     *
     * @param courseInfoVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoVO courseInfoVO) {
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
        return course.getId();
    }

    /**
     * 根据课程id查询课程基本信息 回显
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVO getCourseInfo(String courseId) {
        //1 查询课程表
        Course eduCourse = baseMapper.selectById(courseId);
        CourseInfoVO courseInfoVo = new CourseInfoVO();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        //2 查询描述表
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(courseDescription, courseInfoVo);

        return courseInfoVo;
    }

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCourseInfo(CourseInfoVO courseInfoVo) {
        //1 修改课程表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int update = baseMapper.updateById(course);
        if (update == 0) {
            throw new MyException(20001, "修改课程信息失败");
        }

        //2 修改描述表
        CourseDescription description = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo, description);
        courseDescriptionService.updateById(description);
    }

}
