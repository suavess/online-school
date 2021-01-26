package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.edu.entity.CourseDescription;
import com.suave.edu.mapper.CourseDescriptionMapper;
import com.suave.edu.service.CourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

}
