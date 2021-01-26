package com.suave.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.edu.entity.Subject;
import com.suave.edu.entity.subject.SubjectDTO;
import com.suave.edu.excel.SubjectData;
import com.suave.edu.listener.SubjectExcelListener;
import com.suave.edu.mapper.SubjectMapper;
import com.suave.edu.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-25
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    /**
     * 添加课程分类
     *
     * @param file
     * @param subjectService
     */
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归获取所有课程列表
     *
     * @return
     */
    @Override
    public List<SubjectDTO> getAllSubjectList(List<SubjectDTO> subjectDTOs) {
        if (subjectDTOs == null) {
            subjectDTOs = new ArrayList<>();
            List<Subject> parentSubject = this.list(new QueryWrapper<Subject>().eq("parent_id", 0).orderByAsc("sort"));
            for (Subject subject : parentSubject) {
                SubjectDTO tmp = new SubjectDTO();
                BeanUtils.copyProperties(subject, tmp);
                subjectDTOs.add(tmp);
            }
        }
        for (SubjectDTO subjectDTO : subjectDTOs) {
            String parentId = subjectDTO.getId();
            List<Subject> subjects = this.list(new QueryWrapper<Subject>().eq("parent_id", parentId).orderByAsc("sort"));
            if (subjects != null && subjects.size() > 0) {
                ArrayList<SubjectDTO> child = new ArrayList<>();
                for (Subject subject : subjects) {
                    SubjectDTO tmp = new SubjectDTO();
                    BeanUtils.copyProperties(subject, tmp);
                    child.add(tmp);
                }
                List<SubjectDTO> allSubjectList = getAllSubjectList(child);
                subjectDTO.setChildren(allSubjectList);
            } else {
                subjectDTO.setChildren(null);
            }
        }
        return subjectDTOs;
    }
}
