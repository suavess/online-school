package com.suave.msm.service;

import java.util.HashMap;

/**
 * @author Suave
 * @date 2021/1/29 3:46 下午
 */
public interface MsmService {

    /**
     * 发送短信
     *
     * @param map   带有验证码的map
     * @param phone
     * @return
     */
    boolean send(HashMap<String, Object> map, String phone);

}
