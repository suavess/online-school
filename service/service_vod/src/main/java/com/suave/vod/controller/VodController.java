package com.suave.vod.controller;

import com.suave.common.result.R;
import com.suave.vod.service.VodService;
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
    public R uploadAliVideo(MultipartFile file) {
        String videoId = vodService.uploadAliVideo(file);
        return R.ok().data("videoId", videoId);
    }

    @DeleteMapping("removeAliVideo/{id}")
    public R removeAliVideo(@PathVariable("id") String id) {
        vodService.removeAliVideo(id);
        return R.ok();
    }
}
