package com.suave.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suave.common.result.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void out(HttpServletResponse response, CommonResult commonResult) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), commonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
