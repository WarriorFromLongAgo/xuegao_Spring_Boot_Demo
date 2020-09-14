package com.xuegao.springboot_tool.utils;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：StringUtils
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/30 15:29
 */
public class StringUtils {

    // 如何检查字符串中只包含数字？
    private static void digitsOnlyString(String string) {
        if (string.matches("\\d+")) {
            System.out.println("只包含数字的字符串：" + string);
        }
    }

}