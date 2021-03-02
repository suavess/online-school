package com.suave.edu.controller;


import com.suave.common.result.CommonResult;
import com.suave.edu.entity.Chapter;
import com.suave.edu.entity.chapter.ChapterVO;
import com.suave.edu.service.ChapterService;
import com.suave.edu.service.VideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;

    /**
     * 课程大纲列表
     *
     * @return
     */
    @ApiOperation(value = "课程大纲列表")
    @GetMapping("getChapterVideo/{courseId}")
    public CommonResult getChapterVideo(@PathVariable String courseId) {
        List<ChapterVO> list = chapterService.getChapterVideoByCourseId(courseId);
        return CommonResult.ok().data("allChapterVideo", list);
    }

    /**
     * 添加章节
     *
     * @param chapter
     * @return
     */
    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public CommonResult addChapter(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return CommonResult.ok();
    }

    /**
     * 根据章节id查询
     *
     * @param chapterId
     * @return
     */
    @ApiOperation(value = "根据章节id查询")
    @GetMapping("getChapterInfo/{chapterId}")
    public CommonResult getChapterInfo(@PathVariable String chapterId) {
        Chapter chapter = chapterService.getById(chapterId);
        return CommonResult.ok().data("chapter", chapter);
    }

    /**
     * 修改章节
     *
     * @param chapter
     * @return
     */
    @ApiOperation(value = "修改章节")
    @PostMapping("updateChapter")
    public CommonResult updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return CommonResult.ok();
    }

    /**
     * 删除章节
     *
     * @param chapterId
     * @return
     */
    @ApiOperation(value = "删除章节")
    @DeleteMapping("{chapterId}")
    public CommonResult deleteChapter(@PathVariable String chapterId) {
        return chapterService.deleteChapter(chapterId) ? CommonResult.ok() : CommonResult.error();
    }


}

