package com.suave.base.exception;

import com.suave.common.result.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Suave
 * @date 2021/1/25 3:07 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {

    private static final long serialVersionUID = 5133380081340485940L;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    public MyException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    @Override
    public String toString() {
        return "MSException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}
