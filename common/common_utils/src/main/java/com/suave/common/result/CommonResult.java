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
public class CommonResult {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    //把构造方法私有
    private CommonResult() {
    }

    //成功静态方法
    public static CommonResult ok() {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(ResultEnum.SUCCESS.getSuccess());
        commonResult.setCode(ResultEnum.SUCCESS.getCode());
        commonResult.setMessage(ResultEnum.SUCCESS.getMessage());
        return commonResult;
    }

    //失败静态方法
    public static CommonResult error() {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(ResultEnum.UNKNOWN_REASON.getSuccess());
        commonResult.setCode(ResultEnum.UNKNOWN_REASON.getCode());
        commonResult.setMessage(ResultEnum.UNKNOWN_REASON.getMessage());
        return commonResult;
    }

    //设定结果
    public static CommonResult setResult(ResultEnum resultEnum) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(resultEnum.getSuccess());
        commonResult.setCode(resultEnum.getCode());
        commonResult.setMessage(resultEnum.getMessage());
        return commonResult;
    }

    public CommonResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public CommonResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public CommonResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public CommonResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public CommonResult data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}

