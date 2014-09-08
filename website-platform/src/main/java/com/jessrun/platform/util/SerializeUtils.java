package com.jessrun.platform.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtils {

    private static final Logger log = LoggerFactory.getLogger(SerializeUtils.class);
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * 将JSON字符串反序列化为java对象
     * 
     * @throws IOException
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            log.error("JsonUtil.toObj()", e);
            return null;
        }
    }

    /**
     * 将对象序列化成json
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("JsonUtil.toStr()", e);
            return null;
        }
    }

    public final static Object byteArrayToObj(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayStream = null;
        ObjectInputStream oin = null;
        try {
            byteArrayStream = new ByteArrayInputStream(byteArray);
            oin = new ObjectInputStream(byteArrayStream);
            return oin.readObject();
        } finally {
            IOUtils.closeQuietly(oin);
            IOUtils.closeQuietly(byteArrayStream);
        }
    }

    public final static byte[] toByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayStream = null;
        ObjectOutputStream out = null;
        try {
            byteArrayStream = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteArrayStream);
            out.writeObject(obj);
            return byteArrayStream.toByteArray();
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(byteArrayStream);
        }
    }
}
