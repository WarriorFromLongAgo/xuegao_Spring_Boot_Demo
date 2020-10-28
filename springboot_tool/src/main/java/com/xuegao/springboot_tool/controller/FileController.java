package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.aop.annotation.PrintlnLog;
import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.service.interfaces.IFileService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：FileController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/9 0:33
 */
@RequestMapping(path = "/file")
@RestController
public class FileController {

    private Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFileService fileService;

    /**
     * <br/> @Title: 文件的上传
     * <br/> @MethodName:  uploadFile
     * <br/> @param fileArr:
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<java.lang.String>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/8/24 10:12
     */
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public WrappedResponse<Void> uploadFile(@RequestParam(name = "file", required = false) MultipartFile[] fileArr) throws IOException {
        Void unused = fileService.uploadFileService(fileArr);
        return WrappedResponse.success(unused);
    }

    @PrintlnLog(description = "transform-transform")
    @RequestMapping(path = "/transform", method = RequestMethod.GET)
    public WrappedResponse<Void> transformFile() throws IOException, InterruptedException {
        fileService.transformFile();
        return WrappedResponse.success("转换成功");
    }

    private final String TEMPLATE_FILE_NAME = "template.xlsx";

    @RequestMapping(value = "/downloadTemplate4", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTemplate4(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("download" + File.separator + TEMPLATE_FILE_NAME);
        try (
                // 以流的形式下载文件。
                BufferedInputStream bufferedInputStream = new BufferedInputStream(classPathResource.getInputStream());
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream())
        ) {
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + TEMPLATE_FILE_NAME);
            // response.addHeader("Content-Length", "" + file.length());
            // response.addHeader("Content-Length", String.valueOf(file.length()));
            // response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");

            byte[] buff = new byte[1024];
            int i = bufferedInputStream.read(buff);
            while (i != -1) {
                bufferedOutputStream.write(buff, 0, buff.length);
                bufferedOutputStream.flush();
                i = bufferedInputStream.read(buff);
            }

        } catch (IOException ex) {
            log.error("", ex);
            throw new RuntimeException("下载打包的文件失败！！", ex);
        }

    }

    @RequestMapping(value = "/downloadTemplate5", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTemplate5(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = ResourceUtils.getFile("classpath:download/" + TEMPLATE_FILE_NAME).getPath();
        FileInputStream fileInputStream = new FileInputStream(path);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        //下载文件的默认名称
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        String fileName = TEMPLATE_FILE_NAME;
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);

        workbook.write(response.getOutputStream());
    }

    @RequestMapping(value = "/downloadTemplate6", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTemplate6(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // String path = "download/" + TEMPLATE_FILE_NAME;
        String path = ResourceUtils.getFile("classpath:download/" + TEMPLATE_FILE_NAME).getPath();
        File file = new File(path);

        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + TEMPLATE_FILE_NAME);
        // response.addHeader("Content-Length", "" + file.length());
        response.addHeader("Content-Length", String.valueOf(file.length()));
        // response.setContentType("application/octet-stream");
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");

        if (file.exists()) {
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream())
            ) {
                // byte[] buffer = new byte[bufferedInputStream.available()];
                // bufferedInputStream.read(buffer);
                // response.reset();

                byte[] buff = new byte[1024];
                int i = bufferedInputStream.read(buff);
                while (i != -1) {
                    bufferedOutputStream.write(buff, 0, buff.length);
                    bufferedOutputStream.flush();
                    i = bufferedInputStream.read(buff);
                }

                // bufferedOutputStream.write(buffer);
                // bufferedOutputStream.flush();
                System.out.println("dddddddddddddddddddddddddddddddddddddddddd");
            }
        }
        System.out.println("jkhjdkfhsjkfhsdfhuegrahvjkhvjkxcjbvjfjh");
    }
}