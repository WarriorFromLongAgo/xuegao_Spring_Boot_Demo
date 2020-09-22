package com.xuegao.springboot_tool.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：RedisConvertUtils
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/22 15:38
 */
public class RedisConvertUtils {

    public static <T> List<T> fastJsonDeserializeToSet(Set<Serializable> serializableSet, Class<T> clazz) {
        String s = JSON.toJSONString(serializableSet);
        System.out.println(s);
        List<T> list = JSON.parseObject(s, new TypeReference<List<T>>(clazz) {
        });
        System.out.println(list);
        return list;
    }
}