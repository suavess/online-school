package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.edu.entity.Course;
import com.suave.edu.mapper.CourseMapper;
import com.suave.edu.service.CourseService;
import org.springframework.stereotype.Service;

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

}
