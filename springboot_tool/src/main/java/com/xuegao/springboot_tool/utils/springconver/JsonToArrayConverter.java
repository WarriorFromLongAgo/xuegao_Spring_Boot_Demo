package com.xuegao.springboot_tool.utils.springconver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Set;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils.springconver
 * <br/> @ClassName：JsonToArrayConverter
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/19 16:14
 */
public class JsonToArrayConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() != String.class) {
            return false;
        }
        if (!targetType.getType().isArray()) {
            return false;
        }
        return false;
    }


    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> singleton = Collections.singleton(new ConvertiblePair(String.class, Object[].class));
        return singleton;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        String content = (String) source;
        if (StringUtils.isBlank(content) || "null".equals(content)) {
            return null;
        }
        if (!sourceType.getElementTypeDescriptor().isPrimitive()) {
            if (!content.startsWith("[")) {
                content = "[" + content + "]";
            }
        }
        Object obj = null;
        // jsonchange
        return null;
    }
}