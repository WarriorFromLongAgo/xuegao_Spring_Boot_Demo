package com.xuegao.xuegaomybatis.config;

import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <br/> @PackageName：com.xuegao.xuegaomybatis.config
 * <br/> @ClassName：PropertiesListenerConfig
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/04/18 12:14
 */
public class PropertiesListenerConfig {

    public static Map<String, String> propertiesMap = new HashMap<>();

    private static void processProperties(Properties props) throws BeansException {
        propertiesMap = new HashMap<>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            try {
                // PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties getProperties(String propertyFileName) throws IOException {
        return PropertiesLoaderUtils.loadAllProperties(propertyFileName);
    }

    public static void loadAllProperties(String propertyFileName) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name).toString();
    }

    public static Map<String, String> getAllProperty() {
        return propertiesMap;
    }

    // 作者：慕冬雪
    // 链接：https://www.imooc.com/article/18252
    // 来源：慕课网

}