package com.rpc.common;

import java.io.IOException;

/**
 * 序列化的统一接口
 */
public interface Serialization {

    byte[] serialize(Object obj) throws IOException;

    <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException;

}
