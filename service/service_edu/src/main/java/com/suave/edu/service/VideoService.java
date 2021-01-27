package com.suave.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.edu.entity.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
public interface VideoService extends IService<Video> {

    /**
     * 根据课程id删除小节
     *
     * @param courseId
     */
    void removeVideoByCourseId(String courseId);
}
