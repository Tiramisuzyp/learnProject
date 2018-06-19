package com.rpc.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Test;

import java.io.*;

public class KryoTest {

    /**
     * 使用JDK原生的序列化
     */
    @Test
    public void test0() throws Exception {
        Student ypzeng = new Student("ypzeng");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student0.db"));
        oos.writeObject(ypzeng);
        oos.close();
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream("student0.db"));
        Student ypzengBak = (Student) oin.readObject();
        oin.close();
        assert ypzeng.getName().equals(ypzengBak.getName());
    }


    /**
     * 使用Kryo进行序列化：writeObject应用于待序列化对象不为空的场景
     */
    @Test
    public void test1() throws Exception {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("student1.db"));
        Student ypzeng = new Student("ypzeng");
        kryo.writeObject(output, ypzeng);
        output.close();
        Input input = new Input(new FileInputStream("student1.db"));
        Student ypzengBak = kryo.readObject(input, Student.class);
        input.close();
        assert "ypzeng".equals(ypzengBak.getName());
    }

    /**
     * 使用Kryo进行序列化：writeObjectOrNull应用于待序列化对象可能为空的场景
     */
    @Test
    public void test2() throws Exception {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("student2.db"));
        Student ypzeng = null;
        kryo.writeObjectOrNull(output, ypzeng, Student.class);
        output.close();
        Input input = new Input(new FileInputStream("student2.db"));
        Student ypzengBak = kryo.readObjectOrNull(input, Student.class);
        input.close();
        assert null==ypzengBak;
    }

    /**
     * 使用Kryo进行序列化：writeClassAndObject应用于写入时自动将类信息写入，
     * 而不用手动指定参数。注意：读取的时候还是需要使用instance判断下类型的。
     */
    @Test
    public void test3() throws Exception {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("student3.db"));
        Student ypzeng = new Student("ypzeng");
        kryo.writeClassAndObject(output, ypzeng);
        output.close();
        Input input = new Input(new FileInputStream("student3.db"));
        Object ypzengObj = kryo.readClassAndObject(input);
        input.close();
        Student ypzengBak = null;
        if(ypzengObj instanceof Student){
            ypzengBak = (Student) ypzengObj;
        }
        assert "ypzeng".equals(ypzengBak.getName());
    }

}
