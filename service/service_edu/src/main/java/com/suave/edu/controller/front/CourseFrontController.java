package com.suave.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suave.common.result.CommonResult;
import com.suave.common.utils.JwtUtils;
import com.suave.edu.client.OrderClient;
import com.suave.edu.entity.Course;
import com.suave.edu.entity.CourseWebVO;
import com.suave.edu.entity.chapter.ChapterVO;
import com.suave.edu.entity.frontvo.CourseQueryVO;
import com.suave.edu.service.ChapterService;
import com.suave.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Suave
 * @date 2021/2/1 9:11 上午
 */
@RestController
@RequestMapping("/front/edu/course")
public class CourseFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private OrderClient orderClient;


    /**
     * 获取分页带条件查询的课程信息前台
     *
     * @param page
     * @param limit
     * @param courseQueryVo
     * @return
     */
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public CommonResult getFrontCourseList(@PathVariable("page") long page, @PathVariable("limit") long limit,
                                           @RequestBody(required = false) CourseQueryVO courseQueryVo) {
        Page<Course> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontInfo(pageCourse, courseQueryVo);
        return CommonResult.ok().data(map);
    }

    /**
     * 课程详情的方法
     *
     * @param courseId
     * @return
     */
    @GetMapping("getFrontCourseInfo/{courseId}")
    public CommonResult getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {

        // 1. 根据课程id，编写sql语句来查询课程信息
        CourseWebVO courseWebVo = courseService.getBaseCourseInfo(courseId);

        // 2. 根据课程id获取所有的章节和小节
        List<ChapterVO> chapterList = chapterService.getChapterVideoByCourseId(courseId);

        // 根据课程id和用户id查询当前课程是否已经支付
        // 远程调用
        boolean buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), courseId);

        return CommonResult.ok().data("courseWebVo", courseWebVo).data("chapterList", chapterList).data("isBuy", buyCourse);
    }

}
