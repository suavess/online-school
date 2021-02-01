package com.suave.edu.client;

import org.springframework.stereotype.Component;

/**
 * @author Suave
 * @date 2021/2/1 9:45 上午
 */
@Component
public class OrderClientFallback implements OrderClient {

    @Override
    public boolean isBuyCourse(String memberId, String id) {
        return false;
    }


}
