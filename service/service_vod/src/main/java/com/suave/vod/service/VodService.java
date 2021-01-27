package com.suave.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Suave
 * @date 2021/1/27 10:45 上午
 */
public interface VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    String uploadAliVideo(MultipartFile file);

    /**
     * 删除阿里云视频
     *
     * @param id
     */
    void removeAliVideo(String id);
}
