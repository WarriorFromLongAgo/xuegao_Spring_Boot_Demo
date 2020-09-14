package com.xuegao.springboot_tool.service.impl;

import com.xuegao.springboot_tool.service.interfaces.IFileService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.xslf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：FileServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/27 15:55
 */
@Service
public class FileServiceImpl implements IFileService {
    private final static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final String FILE_MODULE_HEAD = "train.homework/";
    private static final String SUFFIX_PPT = "ppt";
    private static final String SUFFIX_PPTX = "pptx";
    private static final List<String> SUFFIX_PPT_LIST = Arrays.asList("ppt", "pptx", "PPT", "PPTX");
    private static final List<String> SUFFIX_MULTIMEDIA_LIST = Arrays.asList("mp3", "mp4", "MP3", "MP4");

    // 上传文件保存的根路径
    private String saveRootPath = "D:/nfsc/KMS/";

    @Override
    public Void uploadFileService(MultipartFile... fileArr) throws IOException {
        // 然后执行逻辑

        for (MultipartFile file : fileArr) {
            if (ObjectUtils.isEmpty(file)) {
                continue;
            }
            String requestFileName = file.getOriginalFilename();
            // 获取后缀名
            String suffix = getSuffix(requestFileName);
            if (SUFFIX_PPT.equals(suffix)) {
                File transformFile = uploadNormalToFile(file);
                uploadPoiPPT(transformFile, SUFFIX_PPT, file.getOriginalFilename());
            } else if (SUFFIX_PPTX.equals(suffix)) {
                File transformFile = uploadNormalToFile(file);
                uploadPoiPPTX(transformFile, SUFFIX_PPTX, file.getOriginalFilename());
            }
        }
        return null;
    }

    /**
     * <br/> @Title: 获取文件的后缀
     * <br/> @MethodName:  getSuffix
     * <br/> @param fileName:
     * <br/> @Return java.lang.String
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/8/3 17:27
     */
    private String getSuffix(String fileName) {
        if (fileName.lastIndexOf(".") > 0) {
            // 统一 处理返回 小写
            return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        }
        return null;
    }

    public File uploadNormalToFile(MultipartFile... fileArr) throws IOException {
        // 文件名称
        String fileName;
        // 拼接的路径
        String fullPath;
        // List<File> fileList = new ArrayList<File>();
        File returnFile = null;
        // 文件保存的公共路径部分
        // train/homework/时间戳/
        StringBuilder path = new StringBuilder();
        path.append(FILE_MODULE_HEAD);
        for (MultipartFile file : fileArr) {
            // 获取真实的文件名称
            fileName = file.getOriginalFilename();
            // 设置文件保存的名称 uuid
            String fileSaveName = String.valueOf(System.currentTimeMillis());
            // 获取后缀名
            if (fileName.lastIndexOf(".") >= 0) {
                fileSaveName += fileName.substring(fileName.lastIndexOf(".")).replaceAll("-", "");
            }
            // 最终拼接的完整路径
            fullPath = path + fileSaveName;

            // 创建父目录
            File parentPath = new File(this.saveRootPath + path);
            if (!parentPath.exists()) {
                boolean mkdirs = parentPath.mkdirs();
                log.info("mkdirs:" + mkdirs);
            }
            // File savefile = new File(this.saveRootPath + fullPath);
            returnFile = new File(this.saveRootPath + fullPath);

            log.info("文件是否创建成功" + returnFile.exists());
            file.transferTo(returnFile);
            // fileList.add(savefile);
        }


        return returnFile;
    }

    public void uploadPoiPPT(File file, String suffix, String requestFileName) throws IOException {
        FileInputStream inputStream;
        inputStream = new FileInputStream(file.getAbsolutePath());
        HSLFSlideShow hslfSlideShow = new HSLFSlideShow(inputStream);
        // 获取PPT每页的大小（宽和高度）
        Dimension pageSize = hslfSlideShow.getPageSize();
        // 获得PPT文件中的所有的PPT页面（获得每一张幻灯片）,并转为一张张的播放片
        List<HSLFSlide> slideList = hslfSlideShow.getSlides();
        inputStream.close();

        // 文件保存的公共路径部分
        // train/homework/时间戳/
        StringBuilder path = new StringBuilder();
        path.append(FILE_MODULE_HEAD);

        for (int i = 0; i < slideList.size(); i++) {
            HSLFSlide hslfSlide = slideList.get(i);
            BufferedImage bufferedImage = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bufferedImage.createGraphics();
            BufferedImage bufferedImage2 = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2 = bufferedImage2.createGraphics();
            // 这几个循环只要是设置字体为宋体，防止中文乱码，
            List<HSLFShape> shapeList = hslfSlide.getShapes();
            for (int i1 = 0; i1 < shapeList.size(); i1++) {
                HSLFShape shape = shapeList.get(i1);
                if (shape instanceof HSLFAutoShape) {
                    HSLFAutoShape hslfAutoShape = (HSLFAutoShape) shape;
                    System.out.println(" 第 " + (i + 1) + " 页， HSLFAutoShape ");
                } else if (shape instanceof HSLFGroupShape) {
                    HSLFGroupShape hslfGroupShape = (HSLFGroupShape) shape;
                    graphics2.draw(hslfGroupShape.getInteriorAnchor());
                    System.out.println(" 第 " + (i + 1) + " 页， HSLFGroupShape ");
                } else if (shape instanceof HSLFLine) {
                    System.out.println(" 第 " + (i + 1) + " 页， HSLFLine ");
                } else if (shape instanceof HSLFTextBox) {
                    System.out.println(" 第 " + (i + 1) + " 页， HSLFTextBox ");
                } else if (shape instanceof HSLFPictureShape) {
                    HSLFPictureShape hslfPictureShape = (HSLFPictureShape) shape;
                    ShapeType shapeType = hslfPictureShape.getShapeType();
                    System.out.println(shapeType);
                    System.out.println(" 第 " + (i + 1) + " 页， HSLFPictureShape ");
                } else {
                    System.out.println(" 第 " + (i + 1) + " 页， shape " + shape);
                }
            }
            List<List<HSLFTextParagraph>> oneTextParagraphs = hslfSlide.getTextParagraphs();
            for (int i1 = 0; i1 < oneTextParagraphs.size(); i1++) {
                List<HSLFTextParagraph> hslfTextParagraphs = oneTextParagraphs.get(i1);
                for (int i2 = 0; i2 < hslfTextParagraphs.size(); i2++) {
                    HSLFTextParagraph hslfTextRuns = hslfTextParagraphs.get(i2);
                    List<HSLFTextRun> textRuns = hslfTextRuns.getTextRuns();
                    for (int i3 = 0; i3 < textRuns.size(); i3++) {
                        HSLFTextRun hslfTextRun = textRuns.get(i3);
                        hslfTextRun.setFontFamily("宋体");
                    }
                }
            }

            try {
                // hslfSlide.draw(graphics);
                if (!ObjectUtils.isEmpty(graphics2)) {
                    hslfSlide.draw(graphics2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 设置文件保存的名称 uuid
            String fileSaveName = System.currentTimeMillis() + ".png";
            // 获取后缀名
            // String fileName = file.getName();
            // fileSaveName += fileName.substring(fileName.lastIndexOf(".")).replaceAll("-", "");

            // 最终拼接的完整路径
            String fullPath = path + fileSaveName;
            // ipFilePath.append(fullPath).append(",");

            // 创建父目录
            File parentPath = new File(this.saveRootPath + path);
            if (!parentPath.exists()) {
                boolean mkdirs = parentPath.mkdirs();
                log.info("uploadPPT = mkdirs:" + mkdirs);
            }
            // outputStream = new FileOutputStream(this.saveRootPath + fullPath);
            // ImageIO.write(bufferedImage, "png", new File(this.saveRootPath + fullPath));
            ImageIO.write(bufferedImage2, "png", new File(this.saveRootPath + fullPath));
        }
    }

    public void uploadPoiPPTX(File file, String suffix, String requestFileName) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        StringBuilder ipFilePath = new StringBuilder();
        inputStream = new FileInputStream(file.getAbsolutePath());
        XMLSlideShow xmlSlideShow = new XMLSlideShow(inputStream);
        // 获取大小
        Dimension pageSize = xmlSlideShow.getPageSize();
        // 获取幻灯片
        List<XSLFSlide> slideList = xmlSlideShow.getSlides();
        inputStream.close();

        // 文件保存的公共路径部分
        // train/homework/时间戳/
        StringBuilder path = new StringBuilder();
        path.append(FILE_MODULE_HEAD);
        for (XSLFSlide slide : slideList) {
            // 解决乱码问题
            List<XSLFShape> shapeList = slide.getShapes();
            for (XSLFShape shape : shapeList) {
                if (shape instanceof XSLFTextShape) {
                    XSLFTextShape sh = (XSLFTextShape) shape;
                    List<XSLFTextParagraph> textParagraphs = sh.getTextParagraphs();

                    for (XSLFTextParagraph xslfTextParagraph : textParagraphs) {
                        List<XSLFTextRun> textRuns = xslfTextParagraph.getTextRuns();
                        for (XSLFTextRun xslfTextRun : textRuns) {
                            xslfTextRun.setFontFamily("宋体");
                        }
                    }
                }
            }

            // 根据幻灯片大小生成图片
            BufferedImage bufferedImage = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bufferedImage.createGraphics();
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width, pageSize.height));
            // 最核心的代码
            slide.draw(graphics);

            // 设置文件保存的名称 uuid
            String fileSaveName = System.currentTimeMillis() + ".png";
            // 获取后缀名
            // String fileName = file.getName();
            // fileSaveName += fileName.substring(fileName.lastIndexOf(".")).replaceAll("-", "");

            // 最终拼接的完整路径
            String fullPath = path + fileSaveName;
            // ipFilePath.append(fullPath).append(",");

            // 创建父目录
            File parentPath = new File(this.saveRootPath + path);
            if (!parentPath.exists()) {
                boolean mkdirs = parentPath.mkdirs();
                log.info("uploadPPT = mkdirs:" + mkdirs);
            }
            // File savefile = new File(this.saveRootPath + fullPath);
            // log.info("uploadPPT = 文件是否创建成功" + savefile.exists());

            ImageIO.write(bufferedImage, "png", new File(this.saveRootPath + fullPath));
        }

    }

    /**
     * <br/> @Title:
     * <br/> @MethodName:  transformFile
     * <br/>
     * <br/> @Return void
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/3 19:20
     */
    @Override
    public void transformFile() throws IOException, InterruptedException {
        String cmd = "libreoffice6.4 --headless --convert-to pdf:writer_pdf_Export /usr/local/libreoffice/tuomin2.pptx --outdir /usr/local/libreoffice/";
        log.info(cmd);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            // 错误信息
            StreamOutput error = new StreamOutput(process.getErrorStream(), "ERROR");
            // 输出信息（Runtime的输出，即Process的输入）
            StreamOutput output = new StreamOutput(process.getInputStream(), "OUTPUT");
            // 启动执行
            error.start();
            output.start();
            // 子进程退出状态，0表示正常终止
            int exitVal = process.waitFor();
            System.out.println("ExitValue: " + exitVal);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class StreamOutput extends Thread {
    InputStream is;
    String type;

    StreamOutput(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    @Override
    public void run() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(is, "GBK");
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(type + ">" + line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}