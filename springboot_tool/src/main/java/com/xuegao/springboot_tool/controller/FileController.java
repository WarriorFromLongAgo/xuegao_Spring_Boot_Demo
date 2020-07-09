package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Controller
public class FileController {

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView uploadFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

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