package com.suave.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.edu.entity.Chapter;
import com.suave.edu.entity.chapter.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据课程Id查询章节小姐
     *
     * @param courseId
     * @return
     */
    List<ChapterVO> getChapterVideoByCourseId(String courseId);

    /**
     * 删除章节的方法
     *
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);

    /**
     * 根据课程id删除章节
     *
     * @param courseId
     */
    void removeChapterByCourseId(String courseId);

}
