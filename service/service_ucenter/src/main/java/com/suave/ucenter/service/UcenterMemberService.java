package com.suave.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suave.ucenter.entity.RegisterVo;
import com.suave.ucenter.entity.UcenterMember;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Suave
 * @since 2021-01-29
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 登陆
     *
     * @param member
     * @return
     */
    String login(UcenterMember member);

    /**
     * 注册
     *
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据token获取用户的信息
     *
     * @param memberId
     * @return
     */
    UcenterMember getLoginInfo(String memberId);
}
