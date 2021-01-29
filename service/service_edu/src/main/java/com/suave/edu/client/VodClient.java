package com.suave.edu.client;

import com.suave.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign远程调用
 *
 * @author Suave
 * @date 2021/1/27 8:39 下午
 */
@Component
@FeignClient("service-vod")
public interface VodClient {
    @DeleteMapping("/edu/vod/removeAliVideo/{id}")
    R removeAliVideo(@PathVariable("id") String id);
}
