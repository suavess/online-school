package com.suave.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.base.exception.MyException;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.CourseDescription;
import com.suave.edu.entity.CourseWebVO;
import com.suave.edu.entity.frontvo.CourseQueryVO;
import com.suave.edu.entity.vo.CourseInfoVO;
import com.suave.edu.entity.vo.CoursePublishVO;
import com.suave.edu.mapper.CourseMapper;
import com.suave.edu.service.ChapterService;
import com.suave.edu.service.CourseDescriptionService;
import com.suave.edu.service.CourseService;
import com.suave.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id
     * @return
     */
    @Override
    public CoursePublishVO publishCourseInfo(String id) {
        //调用mapper接口方法
        CoursePublishVO publishCourseInfo = this.baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    /**
     * 删除课程
     *
     * @param courseId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourse(String courseId) {
        //1 根据课程id删除小节
        videoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        //失败返回
        if (result == 0) {
            throw new MyException(20001, "删除失败");
        }
    }

    /**
     * 前台带条件的分页查询前台
     *
     * @param pageCourse
     * @param courseQueryVO
     * @return
     */
    @Override
    public Map<String, Object> getCourseFrontInfo(Page<Course> pageCourse, CourseQueryVO courseQueryVO) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        // 判断条件值是否为空
        if (!StrUtil.isEmpty(courseQueryVO.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQueryVO.getSubjectParentId());
        }

        if (!StrUtil.isEmpty(courseQueryVO.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQueryVO.getSubjectId());
        }

        // 这三个字段是做排序，降序
        if (!StrUtil.isEmpty(courseQueryVO.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StrUtil.isEmpty(courseQueryVO.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StrUtil.isEmpty(courseQueryVO.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageCourse, queryWrapper);

        // 获取分页查询之后的详细信息
        // 每页数据
        List<Course> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();

        // 查询之后的数据进行封装
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);


        return map;

    }

    /**
     * 根据课程id查询课程信息，用于前端的展示页面
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseWebVO getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }

    /**
     * 更新课程浏览数
     *
     * @param courseId
     */
    @Override
    public void updatePageViewCount(String courseId) {
        Course eduCourse = baseMapper.selectById(courseId);
        eduCourse.setViewCount(eduCourse.getViewCount() + 1);
        baseMapper.updateById(eduCourse);
    }


}
