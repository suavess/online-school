package com.suave.edu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Suave
 * @date 2021/1/25 8:52 下午
 */
@Data
public class SubjectData {
    /**
     * 1级分类
     */
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    /**
     * 2级分类
     */
    @ExcelProperty(index = 1)
    private String twoSubjectName;

}
