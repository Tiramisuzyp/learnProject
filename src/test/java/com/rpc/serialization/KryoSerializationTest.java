package com.rpc.serialization;

import com.rpc.kryo.Student;
import com.rpc.serialization.KryoSerialization;
import org.junit.Test;

import java.io.IOException;

public class KryoSerializationTest {

    @Test
    public void test0() throws IOException {
        Student student = new Student("ypzeng");
        KryoSerialization kryoSerialization = new KryoSerialization();
        byte[] bytes = kryoSerialization.serialize(student);
        Student studentBak = kryoSerialization.deserialize(bytes, Student.class);
        System.out.println(studentBak.getName());
    }

}
