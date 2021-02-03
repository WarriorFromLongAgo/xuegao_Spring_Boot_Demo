package com.xuegao.springboot_tool.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xuegao.springboot_tool.constant.aop.annotation.PrintlnLog;
import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.service.interfaces.IFileService;
import com.xuegao.springboot_tool.utils.QrCodeUtil;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.EnumMap;
import java.util.Map;

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
                byte[] buff = new byte[1024];
                int i = bufferedInputStream.read(buff);
                while (i != -1) {
                    bufferedOutputStream.write(buff, 0, buff.length);
                    bufferedOutputStream.flush();
                    i = bufferedInputStream.read(buff);
                }
            }
        }
    }


    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    @ResponseBody
    public void qrCode(HttpServletResponse response) throws IOException {
        String myCodeText = "https://www.baidu.com/";
        int size = 512;

        try {
            Map<EncodeHintType, Object> crunchifyHintType = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            crunchifyHintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with version 3.4.1 you could change margin (white border size)
            crunchifyHintType.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            crunchifyHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            QRCodeWriter mYQRCodeWriter = new QRCodeWriter(); // throws com.google.zxing.WriterException
            BitMatrix crunchifyBitMatrix = mYQRCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, crunchifyHintType);
            int CrunchifyWidth = crunchifyBitMatrix.getWidth();

            // The BufferedImage subclass describes an Image with an accessible buffer of crunchifyImage data.
            BufferedImage crunchifyImage = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);

            // Creates a Graphics2D, which can be used to draw into this BufferedImage.
            crunchifyImage.createGraphics();

            // This Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.
            // This is the fundamental class for rendering 2-dimensional shapes, text and images on the Java(tm) platform.
            Graphics2D crunchifyGraphics = (Graphics2D) crunchifyImage.getGraphics();

            // setColor() sets this graphics context's current color to the specified color.
            // All subsequent graphics operations using this graphics context use this specified color.
            crunchifyGraphics.setColor(Color.white);

            // fillRect() fills the specified rectangle. The left and right edges of the rectangle are at x and x + width - 1.
            crunchifyGraphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);

            // TODO: Please change this color as per your need
            crunchifyGraphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (crunchifyBitMatrix.get(i, j)) {
                        crunchifyGraphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            // A class containing static convenience methods for locating
            // ImageReaders and ImageWriters, and performing simple encoding and decoding.
            ImageIO.write(crunchifyImage, "jpg", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}