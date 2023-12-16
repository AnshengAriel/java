package com.ariel.java.base.keyword;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private final static long serialVersionUID = 100L;

    private int id;

    private transient String name;

    private static int age;

    public void setAge(int age) {
        User.age = age;
    }

    public int getAge() {
        return age;
    }
}
