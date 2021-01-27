package com.suave.vod.service.impl;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.suave.vod.constant.VodConstant;
import com.suave.vod.service.VodService;
import com.suave.vod.utils.VodClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Suave
 * @date 2021/1/27 10:45 上午
 */
@Slf4j
@Service
public class VodServiceImpl implements VodService {

    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    @Override
    public String uploadAliVideo(MultipartFile file) {
        try {
            DefaultAcsClient client = VodClientUtil.initVodClient(VodConstant.ACCESS_KEY_ID, VodConstant.ACCESS_KEY_SECRET);
            //accessKeyId, accessKeySecret
            //fileName：上传文件原始名称
            // 01.03.09.mp4
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            // 获取视频上传地址和上传凭证
            CreateUploadVideoResponse response = VodClientUtil.getResponse(client, title, fileName);
            log.info("VideoId: {}", response.getVideoId());
            log.info("UploadAddress: {}", response.getUploadAddress());
            log.info("UploadAuth: {}", response.getUploadAuth());
            log.info("RequestId: {}", response.getRequestId());
            String uploadAddress = Base64.decodeStr(response.getUploadAddress());
            String uploadAuth = Base64.decodeStr(response.getUploadAuth());
            JSONObject uploadAddressJson = JSONObject.parseObject(uploadAddress);
            JSONObject uploadAuthJson = JSONObject.parseObject(uploadAuth);
            String bucketName = uploadAddressJson.getString("Bucket");
            String objectName = uploadAddressJson.getString("FileName");
            // 使用原生oss上传，初始化OssClient
            OSSClient ossClient = VodClientUtil.initOssClient(uploadAuthJson, uploadAddressJson);
            ossClient.putObject(bucketName, objectName, file.getInputStream());
            return response.getVideoId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
