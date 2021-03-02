package com.suave.edu.client;

import com.suave.common.result.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @author Suave
 * @date 2021/1/29 10:19 上午
 */
@Component
public class VodClientFallback implements VodClient {
    @Override
    public CommonResult removeAliVideo(String id) {
        return CommonResult.error().message("删除视频出错了～熔断～");
    }
}
