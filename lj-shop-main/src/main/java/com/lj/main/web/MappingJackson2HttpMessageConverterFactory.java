package com.lj.main.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by xiaoshen.wang on 2018/7/6
 * https://www.cnblogs.com/lic309/p/5048631.html
 */
public class MappingJackson2HttpMessageConverterFactory {

    public MappingJackson2HttpMessageConverter init() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 空值处理为空串
        /*objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException{
                jg.writeString("");
            }
        });*/
        converter.setObjectMapper(objectMapper);
        converter.setSupportedMediaTypes(Lists.newArrayList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        return converter;
    }
}
