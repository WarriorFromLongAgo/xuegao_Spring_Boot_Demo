package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * <br/> @Title: 文件的上传
     * <br/> @MethodName:  uploadFile
     * <br/> @param fileArr:
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<java.lang.String>
     * <br/> @Description:
     * <br/> @author: 80004960
     * <br/> @date:  2020/8/24 10:12
     */
    @ResponseBody
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public WrappedResponse<String> uploadFile(@RequestParam(name = "file", required = false) MultipartFile[] fileArr) {
        for (MultipartFile file : fileArr) {
            String fileName = file.getName();
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            System.out.println(fileName);
            System.out.println(originalFilename);
            System.out.println(contentType);
        }
        return WrappedResponse.success();
    }
}