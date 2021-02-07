package com.xuegao.springboot_tool.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.EnumMap;
import java.util.Map;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：QrCodeUtil
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/02/03 18:14
 */
public class QrCodeUtil {

    private static final Logger log = LoggerFactory.getLogger(QrCodeUtil.class);

    static int size = 512;
    static String imageType = "png";

    /**
     * <br/> @Title: 创建二维码
     * <br/> @Description:
     * <br/> @MethodName: createQrCode
     * <br/> @param codeText:
     * <br/> @param outputStream:
     * <br/> @return: void
     * <br/> @author: xuegao
     * <br/> @date: 2021/02/07 18:13
     */
    public static void createQrCode(String codeText, OutputStream outputStream) {
        try {

            Map<EncodeHintType, Object> hintTypeObjectMap = new EnumMap<>(EncodeHintType.class);
            // "UTF-8" must string, no modify
            hintTypeObjectMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with version 3.4.1 you could change margin (white border size)
            /* default = 4 */
            hintTypeObjectMap.put(EncodeHintType.MARGIN, 1);
            hintTypeObjectMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            // throws com.google.zxing.WriterException
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(codeText, BarcodeFormat.QR_CODE, size, size, hintTypeObjectMap);
            int width = bitMatrix.getWidth();

            // The BufferedImage subclass describes an Image with an accessible buffer of bufferedImage data.
            BufferedImage bufferedImage = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);

            // Creates a Graphics2D, which can be used to draw into this BufferedImage.
            bufferedImage.createGraphics();

            // This Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.
            // This is the fundamental class for rendering 2-dimensional shapes, text and images on the Java(tm) platform.
            Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();

            // setColor() sets this graphics context's current color to the specified color.
            // All subsequent graphics operations using this graphics context use this specified color.
            graphics2D.setColor(Color.white);

            // fillRect() fills the specified rectangle. The left and right edges of the rectangle are at x and x + width - 1.
            graphics2D.fillRect(0, 0, width, width);

            // Please change this color as per your need
            graphics2D.setColor(Color.BLACK);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics2D.fillRect(i, j, 1, 1);
                    }
                }
            }

            // A class containing static convenience methods for locating
            // ImageReaders and ImageWriters, and performing simple encoding and decoding.
            ImageIO.write(bufferedImage, imageType, outputStream);

        } catch (WriterException | IOException e) {
            log.error("QrCodeUtil createQrCode ".concat(e.getMessage()));
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String myCodeText = "https://www.baidu.com/";
        String filePath = "D:\\nfsc\\KMS\\train.file\\classfile\\" + System.currentTimeMillis() + ".jpg";
        File file = new File(filePath);
        try {
            createQrCode(myCodeText, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}