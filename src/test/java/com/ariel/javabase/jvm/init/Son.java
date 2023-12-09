package com.ariel.javabase.jvm.init;

public class Son extends Father {
    int i = 30;

    /**
     * 普通成员变量赋值会整合到构造代码块中
     * 构造代码块会整合到所有构造器中
     * 所有构造器的第一行都是先初始化父类构造器
     */
    public Son() {
        super();
        this.print();
        i = 40;
    }
    public Son(int i) {
        this.print();
        i = 40;
    }
    @Override
    public void print() {
        System.out.println("Son.i=" + i);
    }

    public static void main(String[] args) {
        Father father = new Son();
        System.out.println(father.i);
        Son son = (Son) father;
        System.out.println(son.i);
        System.out.println(father == son);
    }
}
