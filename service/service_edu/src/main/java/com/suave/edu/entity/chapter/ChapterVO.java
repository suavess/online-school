package com.suave.edu.entity.chapter;

import lombok.Data;

import java.util.List;

/**
 * @author Suave
 * @date 2021/1/26 3:45 下午
 */
@Data
public class ChapterVO {
    private String id;
    private String title;
    private List<VideoVO> children;
}
