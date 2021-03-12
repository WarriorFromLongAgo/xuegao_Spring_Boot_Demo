package com.xuegao.springboot_tool.constant.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.constant.json
 * <br/> @ClassName：LongJsonSerializer
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/4/6 21:48
 */
public class LongJsonDeSerializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}