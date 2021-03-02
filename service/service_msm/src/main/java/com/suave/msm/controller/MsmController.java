package com.suave.msm.controller;

import cn.hutool.core.util.StrUtil;
import com.suave.common.result.CommonResult;
import com.suave.msm.service.MsmService;
import com.suave.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Suave
 * @date 2021/1/29 3:45 下午
 */
@RestController
@RequestMapping("/edu/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public CommonResult sendMs(@PathVariable String phone) {

        // 由于没有阿里云的短信服务，这里都能从redis中获取到验证码
        // 之后有的话再进行修改
//        redisTemplate.opsForValue().set(phone,"1211",5, TimeUnit.MINUTES);

        // 1. 从redis中获取验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (StrUtil.isNotEmpty(code)) {
            return CommonResult.ok();
        }

        // 2. 从redis中获取不到
        // 生成四位的随机数
        String code1 = RandomUtil.getFourBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code1);
        // 发送短信
        boolean isSend = msmService.send(map, phone);
        if (isSend) {
            // 发送成功， 把验证码放到redis中，设置有效时间
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

            return CommonResult.ok();
        } else {
            return CommonResult.error().message("短信发送失败");
        }
    }

}
