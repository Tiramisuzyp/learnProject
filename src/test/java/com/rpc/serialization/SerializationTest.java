package com.rpc.serialization;

import com.rpc.kryo.Student;
import com.rpc.serialization.KryoSerialization;
import org.junit.Test;

import java.io.IOException;

public class SerializationTest {

    @Test
    public void kryoTest() throws IOException {
        Student student = new Student("ypzeng");
        KryoSerialization kryoSerialization = new KryoSerialization();
        byte[] bytes = kryoSerialization.serialize(student);
        Student studentBak = kryoSerialization.deserialize(bytes, Student.class);
        System.out.println(studentBak.getName());
        System.out.println(bytes.length);
    }

    @Test
    public void hessianTest() throws IOException {
        Student student = new Student("ypzeng");
        HessianSerialization hessianSerialization = new HessianSerialization();
        byte[] bytes = hessianSerialization.serialize(student);
        Student studentBak = hessianSerialization.deserialize(bytes, Student.class);
        System.out.println(studentBak.getName());
        System.out.println(bytes.length);
    }


}
