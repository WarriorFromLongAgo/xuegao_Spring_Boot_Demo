package com.xuegao.springboot_tool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/***
 * <br/> @Title:
 * <br/> @MethodName:  异常信息文件工具类
 * <br/> @Return
 * <br/> @Description: 
 * <br/> @date:  2020/4/5 15:29
 */
public class ExceptionUtil {
    private static final Logger log = LoggerFactory.getLogger(ExceptionUtil.class);

    /**
     * 打印异常信息
     */
    public static String getMessage(Exception e) {
        String swStr = null;
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            swStr = sw.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
        return swStr;
    }
}