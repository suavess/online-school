package com.suave.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件服务类
 *
 * @author Suave
 * @date 2021/1/25 5:21 下午
 */
public interface OssService {

    /**
     * 上传头像到oss
     */
    String uploadFileAvatar(MultipartFile file);

    /**
     * 阿里云oss 文件删除
     *
     * @param url 文件的url地址
     */
    void removeFile(String url);

}
