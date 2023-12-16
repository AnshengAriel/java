package com.ariel.java.base.jvm.init;

/**
 * 双亲委派模型
 * jvm按需加载类，当需要加载一个类时，子类加载器会先向上委托，交由父类加载器处理，父类处理不了的会向下委托。
 * 因此自定义的java.lang.String根本没有加载进去，它实际上被引导类加载忽略了
 */
public class ParentsModel {

    public static void main(String[] args) {
        java.lang.String string = new java.lang.String();
        System.out.println("string.getClass().getClassLoader() = " + string.getClass().getClassLoader());
    }

}
