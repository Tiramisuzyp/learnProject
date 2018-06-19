package com.rpc.serialization;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.rpc.common.Serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Kryo：为java定制的序列化协议。序列化后字节数少，利于网络传输。但是不支持跨语言（或支持的代价大）。
 * dubbox扩展中支持Kryo序列化协议。
 */
public class KryoSerialization implements Serialization{

    private static final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            return kryo;
        }
    };

    public byte[] serialize(Object obj) throws IOException {
        Kryo kryo = kryoLocal.get();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeObject(output, obj);
        output.close();
        return bos.toByteArray();
    }

    public <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException {
        Kryo kryo = kryoLocal.get();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Input input = new Input(bis);
        input.close();
        return kryo.readObject(input, clz);
    }

}
