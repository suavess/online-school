package com.suave.edu.client;

import com.suave.common.result.R;
import org.springframework.stereotype.Component;

/**
 * @author Suave
 * @date 2021/1/29 10:19 上午
 */
@Component
public class VodClientFallback implements VodClient {
    @Override
    public R removeAliVideo(String id) {
        return R.error().message("删除视频出错了～熔断～");
    }
}
