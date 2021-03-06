package com.suave.vod.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;

/**
 * 阿里云视频点播工具类
 *
 * @author Suave
 * @date 2021/1/27 10:52 上午
 */
public class VodClientUtil {

    /**
     * 初始化上传客户端
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws ClientException
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * 获取视频上传地址及凭证
     *
     * @param client
     * @param title
     * @param fileName
     * @return
     * @throws ClientException
     */
    public static CreateUploadVideoResponse getResponse(DefaultAcsClient client, String title, String fileName) throws ClientException {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(title);
        request.setFileName(fileName);
        return client.getAcsResponse(request);
    }

    /**
     * maven仓库不包含阿里云上传视频sdk
     * 使用原生oss上传
     *
     * @param uploadAuth
     * @param uploadAddress
     * @return
     */
    public static OSSClient initOssClient(JSONObject uploadAuth, JSONObject uploadAddress) {
        String endpoint = uploadAddress.getString("Endpoint");
        String accessKeyId = uploadAuth.getString("AccessKeyId");
        String accessKeySecret = uploadAuth.getString("AccessKeySecret");
        String securityToken = uploadAuth.getString("SecurityToken");
        return new OSSClient(endpoint, accessKeyId, accessKeySecret, securityToken);
    }

    /**
     * 删除视频
     *
     * @param client 发送请求客户端
     * @return DeleteVideoResponse 删除视频响应数据
     * @throws Exception
     */
    public static DeleteVideoResponse deleteVideo(DefaultAcsClient client, String videos) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videos);
        return client.getAcsResponse(request);
    }

}
