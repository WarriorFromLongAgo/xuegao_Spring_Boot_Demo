package com.xuegao.springboot_tool.config.serializer;

import org.nustaq.serialization.FSTConfiguration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Description: Fst 序列化.<br>
 *
 * @author m-xy
 * Created By 2019/8/20 下午3:36
 */
public class FstSerializer<T> implements RedisSerializer<T> {

    private static FSTConfiguration configuration = FSTConfiguration.createStructConfiguration();
    private Class<T> clazz;

    public FstSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
        configuration.registerClass(clazz);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return configuration.asByteArray(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return (T) configuration.asObject(bytes);
    }
}