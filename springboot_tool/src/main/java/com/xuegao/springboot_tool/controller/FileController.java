package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.service.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：FileController
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/7/9 0:33
 */
@RequestMapping(path = "/file")
@RestController
public class FileController {

    @Autowired
    private IFileService fileService;

    /**
     * <br/> @Title: 文件的上传
     * <br/> @MethodName:  uploadFile
     * <br/> @param fileArr:
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<java.lang.String>
     * <br/> @Description:
     * <br/> @author: 80004960
     * <br/> @date:  2020/8/24 10:12
     */
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public WrappedResponse<Void> uploadFile(@RequestParam(name = "file", required = false) MultipartFile[] fileArr) throws IOException {
        Void unused = fileService.uploadFileService(fileArr);
        return WrappedResponse.success(unused);
    }
}