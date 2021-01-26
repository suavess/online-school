package com.suave.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.edu.entity.Chapter;
import com.suave.edu.mapper.ChapterMapper;
import com.suave.edu.service.ChapterService;
import org.springframework.stereotype.Service;

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

}
