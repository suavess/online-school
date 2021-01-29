package com.suave.msm.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.suave.msm.constant.MsmConstant;
import com.suave.msm.service.MsmService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Suave
 * @date 2021/1/29 3:46 下午
 */
@Service
public class MsmServiceImpl implements MsmService {

    /**
     * 发送短信
     *
     * @param map   带有验证码的map
     * @param phone
     * @return
     */
    @Override
    public boolean send(HashMap<String, Object> map, String phone) {
        if (StrUtil.isEmpty(phone)) {
            return false;
        }

        DefaultProfile profile =
                DefaultProfile.getProfile("default", MsmConstant.KEY_ID, MsmConstant.KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        // 固定参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");


        // 根据阿里云短信自己配置，签名信息
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "");
        request.putQueryParameter("TemplateCode", "");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        // 最终发送
        try {
            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
