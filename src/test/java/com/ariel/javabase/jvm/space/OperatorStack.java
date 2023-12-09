package com.ariel.javabase.jvm.space;

/**
 * 局部变量表的操作
 */
public class OperatorStack {

    public static void main(String[] args) {
        System.out.println();
    }

    public int m1() {
        int i = 9;
        return i;
    }

    /**
     *  0 aload_0    this入栈
     *  1 invokevirtual #4 <com/ariel/jvm/OperatorStack.m1 : ()I>    this出栈->反射调用m1(this)->结果入栈
     *  4 istore_1    结果出栈->保存到局部变量表位置1
     *  5 getstatic #2 <java/lang/System.out : Ljava/io/PrintStream;>    获取静态对象并入栈
     *  8 iload_1    局部变量表位置1入栈，此时栈中包含两个元素
     *  9 invokevirtual #5 <java/io/PrintStream.println : (I)V>    对象PrintStream、参数i依次出栈->反射调用println(o,i)->无返回值不处理
     * 12 return    无返回值，结束
     */
    public void m2() {
        int i = m1();
        System.out.println(i);
    }

}
