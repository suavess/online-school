package com.suave.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.suave.base.exception.MyException;
import com.suave.common.result.CommonResult;
import com.suave.vod.constant.VodConstant;
import com.suave.vod.service.VodService;
import com.suave.vod.utils.VodClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Suave
 * @date 2021/1/27 10:44 上午
 */
@RestController
@RequestMapping("/edu/vod")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     *
     * @return
     */
    @PostMapping("uploadAliVideo")
    public CommonResult uploadAliVideo(MultipartFile file) {
        String videoId = vodService.uploadAliVideo(file);
        return CommonResult.ok().data("videoId", videoId);
    }

    /**
     * 根据视频id删除阿里云中的视频
     *
     * @param id
     * @return
     */
    @DeleteMapping("removeAliVideo/{id}")
    public CommonResult removeAliVideo(@PathVariable("id") String id) {
        vodService.removeAliVideo(id);
        return CommonResult.ok();
    }

    /**
     * 根据视频id获取到视频播放凭证
     *
     * @param id
     * @return
     */
    @GetMapping("getPalyAuth/{id}")
    public CommonResult getPlayAuth(@PathVariable String id) {
        // 创建初始化对象
        DefaultAcsClient client = VodClientUtil.initVodClient(VodConstant.ACCESS_KEY_ID, VodConstant.ACCESS_KEY_SECRET);

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(id);

        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String PlayAuth = response.getPlayAuth();
            return CommonResult.ok().data("PlayAuth", PlayAuth);
        } catch (Exception e) {
            throw new MyException(20001, "获取凭证失败");
        }
    }
}
