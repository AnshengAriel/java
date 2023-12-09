package com.ariel.javabase.reflection.a;

import lombok.Data;

@D
@Data
public class F extends A implements B {

    @D
    private String name;

    @D
    private Integer age;

    public F() {}

    private F(String name) {
        this.name = name;
    }

    public F(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @D
    public void named(@D String name) {
        System.out.println(name);
    }

    @D
    public static void hello(@D int age, String name) {
        System.out.println(age);
    }

    @D
    private void hha() {}

}
