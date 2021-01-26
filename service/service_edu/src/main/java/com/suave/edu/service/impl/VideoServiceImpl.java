package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.edu.entity.Video;
import com.suave.edu.mapper.VideoMapper;
import com.suave.edu.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-26
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
