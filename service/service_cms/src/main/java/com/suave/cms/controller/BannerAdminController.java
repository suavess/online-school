package com.suave.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suave.cms.entity.Banner;
import com.suave.cms.service.BannerService;
import com.suave.common.result.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * 管理员页面，对于轮播图的增删改查
 * </p>
 *
 * @author Suave
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/cms/admin/banner")
public class BannerAdminController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("pageBanner/{page}/{limit}")
    public CommonResult pageBanner(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Integer limit) {
        Page<Banner> pageParam = new Page<>(page, limit);
        bannerService.page(pageParam);
        return CommonResult.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    /**
     * 添加
     *
     * @param banner
     * @return
     */
    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public CommonResult save(@RequestBody Banner banner) {
        bannerService.save(banner);
        return CommonResult.ok();
    }


    /**
     * 修改
     *
     * @param banner
     * @return
     */
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public CommonResult updateById(@RequestBody Banner banner) {
        bannerService.updateById(banner);
        return CommonResult.ok();
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable String id) {
        bannerService.removeById(id);
        return CommonResult.ok();
    }


}

