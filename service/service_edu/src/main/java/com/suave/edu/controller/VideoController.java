package com.suave.edu.controller;


import com.suave.common.result.R;
import com.suave.edu.entity.Video;
import com.suave.edu.service.VideoService;
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

    /**
     * 添加小节
     *
     * @param video
     * @return
     */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video video) {
        videoService.save(video);
        return R.ok();
    }

    /**
     * 删除小节
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable("id") String id) {
        videoService.removeById(id);
        return R.ok();
    }

    /**
     * 删除小节
     *
     * @param video
     * @return
     */
    @PutMapping("updateVideo")
    public R updateVideo(@RequestBody Video video) {
        videoService.updateById(video);
        return R.ok();
    }


}

