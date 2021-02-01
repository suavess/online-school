package com.suave.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Suave
 * @date 2021/2/1 9:42 上午
 */

@Component
@FeignClient(value = "service-order", fallback = OrderClientFallback.class)
public interface OrderClient {
    /**
     * 根据用户id和课程id查询订单信息
     *
     * @param memberId
     * @param id
     * @return
     */
    @GetMapping("/edu/order/isBuyCourse/{memberId}/{id}")
    boolean isBuyCourse(@PathVariable("memberId") String memberId, @PathVariable("id") String id);
}
