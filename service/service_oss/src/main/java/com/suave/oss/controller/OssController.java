package com.suave.oss.controller;

import com.suave.common.result.CommonResult;
import com.suave.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Suave
 * @date 2021/1/25 5:19 下午
 */
@Api(tags = "上传文件")
@RestController
@RequestMapping("/edu/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    /**
     * 上传头像的方法
     */
    @ApiOperation(value = "上传头像的方法")
    @PostMapping
    public CommonResult uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return CommonResult.ok().data("url", url);
    }

    @ApiOperation(value = "文件删除")
    @DeleteMapping("remove")
    public CommonResult removeFile(
            @ApiParam(value = "要删除的文件url路径", required = true)
            @RequestBody String url) {
        ossService.removeFile(url);
        return CommonResult.ok().message("文件删除成功");
    }

}
