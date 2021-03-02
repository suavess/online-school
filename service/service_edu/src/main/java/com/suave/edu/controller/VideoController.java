package com.suave.edu.controller;


import cn.hutool.core.util.StrUtil;
import com.suave.base.exception.MyException;
import com.suave.common.result.CommonResult;
import com.suave.edu.client.VodClient;
import com.suave.edu.entity.Video;
import com.suave.edu.service.VideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/edu/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;

    /**
     * 添加小节
     *
     * @param video
     * @return
     */
    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public CommonResult addVideo(@RequestBody Video video) {
        videoService.save(video);
        return CommonResult.ok();
    }

    /**
     * 删除小节
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除小节")
    @DeleteMapping("{id}")
    public CommonResult deleteVideo(@PathVariable("id") String id) {
        videoService.removeById(id);
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        //判断小节里面是否有视频id
        if (StrUtil.isNotEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            CommonResult result = vodClient.removeAliVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new MyException(20001, "删除视频失败~熔断了～");
            }
        }
        //删除小节
        videoService.removeById(id);
        return CommonResult.ok();
    }

    /**
     * 删除小节
     *
     * @param video
     * @return
     */
    @PutMapping("updateVideo")
    public CommonResult updateVideo(@RequestBody Video video) {
        videoService.updateById(video);
        return CommonResult.ok();
    }


}

