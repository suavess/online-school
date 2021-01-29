package com.suave.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.cms.entity.Banner;
import com.suave.cms.mapper.BannerMapper;
import com.suave.cms.service.BannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-29
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    /**
     * 查询所有banner
     *
     * @return
     */
    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<Banner> selectAllBanner() {
        // 根据id进行降序排列，并显示排序之后的前两条数据
        // 根据显示规则进行修改
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        return this.list(wrapper);
    }
}
