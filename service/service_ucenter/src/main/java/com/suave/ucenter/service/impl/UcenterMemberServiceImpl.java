package com.suave.ucenter.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suave.base.exception.MyException;
import com.suave.common.result.ResultEnum;
import com.suave.common.utils.JwtUtils;
import com.suave.ucenter.entity.RegisterVo;
import com.suave.ucenter.entity.UcenterMember;
import com.suave.ucenter.mapper.UcenterMemberMapper;
import com.suave.ucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Suave
 * @since 2021-01-29
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登陆
     *
     * @param member
     * @return
     */
    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StrUtil.isBlank(mobile) || StrUtil.isBlank(password)) {
            throw new MyException(ResultEnum.LOGIN_ERROR);
        }
        UcenterMember exist = this.baseMapper.selectOne(
                new QueryWrapper<UcenterMember>()
                        .eq("mobile", mobile)
        );
        if (exist == null || exist.getIsDisabled()) {
            throw new MyException(ResultEnum.LOGIN_ERROR);
        }
        if (exist.getPassword().equals(SecureUtil.md5(member.getPassword()))) {
            throw new MyException(ResultEnum.LOGIN_PASSWORD_ERROR);
        }
        return JwtUtils.getJwtToken(exist.getId(), exist.getNickname());
    }

    /**
     * 注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if (StrUtil.isEmpty(mobile) ||
                StrUtil.isEmpty(mobile) ||
                StrUtil.isEmpty(password) ||
                StrUtil.isEmpty(code)) {
            throw new MyException(ResultEnum.REGISTER_ERROR);
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(mobile).toString();
        if (!code.equals(mobleCode)) {
            throw new MyException(ResultEnum.CODE_ERROR);
        }

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (count.intValue() > 0) {
            throw new MyException(ResultEnum.REGISTER_ERROR);
        }

        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(SecureUtil.md5(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }

    /**
     * 根据token获取用户的信息
     *
     * @param memberId
     * @return
     */
    @Override
    public UcenterMember getLoginInfo(String memberId) {
        UcenterMember member = baseMapper.selectById(memberId);
        return member;
    }


}
