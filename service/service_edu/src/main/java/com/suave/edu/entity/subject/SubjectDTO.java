package com.suave.edu.entity.subject;

import lombok.Data;

import java.util.List;

/**
 * @author Suave
 * @date 2021/1/25 8:47 下午
 */
@Data
public class SubjectDTO {

    private String id;
    private String title;

    /**
     * 一个一级分类有多个二级分类
     */
    private List<SubjectDTO> children;


}
