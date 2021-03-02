package com.suave.cms.controller;

import com.suave.cms.service.BannerService;
import com.suave.common.result.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Suave
 * @date 2021/1/29 10:47 上午
 */
@Api(tags = "网站首页Banner列表")
@RestController
@RequestMapping("/cms/front/banner")
public class BannerFrontController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("getAllBanner")
    public CommonResult getAllBanner() {
        return CommonResult.ok().data("bannerList", bannerService.selectAllBanner());
    }


}
