package com.suave.ucenter.controller;


import com.suave.base.exception.MyException;
import com.suave.common.result.CommonResult;
import com.suave.common.result.ResultEnum;
import com.suave.common.utils.JwtUtils;
import com.suave.ucenter.entity.RegisterVo;
import com.suave.ucenter.entity.UcenterMember;
import com.suave.ucenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Suave
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/ucenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    /**
     * 返回token值
     *
     * @param member
     * @return
     */
    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public CommonResult login(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return CommonResult.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public CommonResult register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return CommonResult.ok();
    }

    /**
     * 根据token获取用户的登录信息
     *
     * @param request 前端把token放入到request中
     * @return
     */
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("getMemberInfo")
    public CommonResult getLoginInfo(HttpServletRequest request) {
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            // 通过用户id在数据库中进行查询该用户的信息
            UcenterMember loginInfo = memberService.getLoginInfo(memberId);
            return CommonResult.ok().data("userInfo", loginInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultEnum.FETCH_USERINFO_ERROR);
        }
    }


}

