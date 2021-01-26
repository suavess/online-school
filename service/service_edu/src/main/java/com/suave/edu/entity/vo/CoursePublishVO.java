package com.suave.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Suave
 * @date 2021/1/27 12:10 上午
 */
@Data
@ApiModel("课程发布信息")
public class CoursePublishVO implements Serializable {
    private static final long serialVersionUID = 5253034715287586119L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    /**
     * 只用于显示
     */
    private String price;

}
