package com.suave.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.cms.entity.Banner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author Suave
 * @since 2021-01-29
 */
public interface BannerService extends IService<Banner> {

    /**
     * 查询所有banner
     *
     * @return
     */
    List<Banner> selectAllBanner();
}
