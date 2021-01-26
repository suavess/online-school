package com.suave.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程列表条件查询
 *
 * @author Suave
 * @date 2021/1/26 8:42 下午
 */
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "状态 Draft未发布 Normal已发布")
    private String status;

}
