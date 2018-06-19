package com.rpc.kryo;

import java.io.Serializable;

/**
 * 使用kryo序列化的类必须拥有无参构造器
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 666L;

    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
