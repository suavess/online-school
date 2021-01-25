package com.suave.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回类
 *
 * @author Suave
 * @date 2021/1/25 12:29 上午
 */
@Data
@AllArgsConstructor
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    //把构造方法私有
    private R() {
    }

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(ResultEnum.SUCCESS.getSuccess());
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getMessage());
        return r;
    }

    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(ResultEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ResultEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    //设定结果
    public static R setResult(ResultEnum resultEnum) {
        R r = new R();
        r.setSuccess(resultEnum.getSuccess());
        r.setCode(resultEnum.getCode());
        r.setMessage(resultEnum.getMessage());
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}

