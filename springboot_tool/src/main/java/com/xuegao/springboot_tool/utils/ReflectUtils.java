package com.xuegao.springboot_tool.utils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：ReflectUtils
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/10 16:34
 */
public class ReflectUtils {


    public static void setDefault() {
        // for (Field field : reportDTO1.getClass().getDeclaredFields()) {
        //     field.setAccessible(true);
        //     try {
        //         if ("java.lang.String".equals(field.getType().getName())) {
        //             field.set(reportDTO1, "");
        //         }
        //         if ("java.lang.Integer".equals(field.getType().getName())) {
        //             field.set(reportDTO1, 0);
        //         }
        //         if ("java.util.Date".equals(field.getType().getName())) {
        //             field.set(reportDTO1, new Date());
        //         }
        //     } catch (IllegalAccessException e) {
        //         e.printStackTrace();
        //     }
        // }
    }
}