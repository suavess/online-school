package com.suave.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.edu.entity.Subject;
import com.suave.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Suave
 * @since 2021-01-25
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 添加课程分类
     */
    void saveSubject(MultipartFile file, SubjectService SubjectService);

    /**
     * 课程分类列表功能
     */
    List<OneSubject> getAllOneTwoSubject();

}
