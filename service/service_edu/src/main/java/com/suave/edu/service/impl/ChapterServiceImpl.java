package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.base.exception.MyException;
import com.suave.edu.entity.Chapter;
import com.suave.edu.entity.Video;
import com.suave.edu.entity.chapter.ChapterVO;
import com.suave.edu.entity.chapter.VideoVO;
import com.suave.edu.mapper.ChapterMapper;
import com.suave.edu.service.ChapterService;
import com.suave.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    /**
     * 根据课程Id查询章节小姐
     *
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVO> getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> eduChapters = baseMapper.selectList(wrapperChapter);

        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<Video> eduVideos = videoService.list(wrapperVideo);

        //创建list集合，用于最终封装数据
        List<ChapterVO> finalList = new ArrayList<>();

        //3 遍历查询章节list集合进行封装
        //遍历查询章节list集合
        for (int i = 0; i < eduChapters.size(); i++) {
            //得到每个章节
            Chapter eduChapter = eduChapters.get(i);
            //eduChapter对象值复制到ChapterVo里面
            ChapterVO chapterVo = new ChapterVO();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            //把chapterVo放到最终list集合
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVO> videoList = new ArrayList<>();

            //4 遍历查询小节list集合，进行封装
            for (int m = 0; m < eduVideos.size(); m++) {
                //得到每个小节
                Video video = eduVideos.get(m);
                //判断：小节里面chapterid和章节里面id是否一样
                if (video.getChapterId().equals(eduChapter.getId())) {
                    //进行封装
                    VideoVO videoVo = new VideoVO();
                    BeanUtils.copyProperties(video, videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }

            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

    /**
     * 删除章节的方法
     *
     * @param chapterId
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterId章节id 查询小节表，如果查询数据，不进行删除
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        //需要数据list  不需要数据  返回值判断章节下有无小节
        int count = videoService.count(wrapper);
        //判断
        //查询出小节，不进行删除
        if (count > 0) {
            throw new MyException(20001, "该章节下还有小节，无法删除哦");
        }
        //删除章节
        int result = baseMapper.deleteById(chapterId);
        //成功  1>0   0>0
        return result > 0;

    }

    /**
     * 根据课程id删除章节
     *
     * @param courseId
     */
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }

}
