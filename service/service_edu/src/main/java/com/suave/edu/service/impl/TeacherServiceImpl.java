package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.edu.entity.Teacher;
import com.suave.edu.mapper.TeacherMapper;
import com.suave.edu.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-24
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

}
