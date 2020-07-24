package com.xuegao.springboot_tool.config.serializer;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author create by liming sun on 2020-07-21 14:09:51
 * @desc 对字符串序列化新增前缀
 */
public class PrefixStringKeySerializer extends StringRedisSerializer {
    private Charset charset = StandardCharsets.UTF_8;
    private RedisKeyPrefixProperties prefix;

    public PrefixStringKeySerializer(){
        this(new RedisKeyPrefixProperties());
    }

    public PrefixStringKeySerializer(RedisKeyPrefixProperties prefix) {
        super();
        this.prefix = prefix;
    }

    @Override
    public String deserialize(@Nullable byte[] bytes) {
        String saveKey = new String(bytes, charset);
        if (prefix.getEnable() != null && prefix.getEnable()) {
            String prefixKey = spliceKey(prefix.getKey());
            int indexOf = saveKey.indexOf(prefixKey);
            if (indexOf > 0) {
                saveKey = saveKey.substring(indexOf);
            }
        }
        return (saveKey.getBytes() == null ? null : saveKey);
    }

    @Override
    public byte[] serialize(@Nullable String key) {
        if (prefix.getEnable() != null && prefix.getEnable()) {
            key = spliceKey(prefix.getKey()) + key;
        }
        return (key == null ? null : key.getBytes(charset));
    }

    private String spliceKey(String prefixKey) {
        if (StringUtils.isNotBlank(prefixKey) && !prefixKey.endsWith(":")) {
            prefixKey = prefixKey + "::";
        }
        return prefixKey;
    }
}