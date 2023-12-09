package com.ariel.javabase.jvm.space;

import java.lang.reflect.Field;

public class StringTable {

    public static void main(String[] args) throws InterruptedException {
        String s = new String(new char[]{'a', 'b', 'c'});
        String s0 = "abc"; // 此时常量池：entrySet[0]={hash:abc, k:s0(abc)}
        String s1 = "def"; // 此时常量池：entrySet[1]={hash:def, k:s1(def)}
        String s2 = "ghi"; // 此时常量池：entrySet[2]={hash:ghi, k:s2(ghi)}
        String s3 = new String(s0);

        alterStr(s0, new char[]{'d', 'e', 'f'}); // 修改常量池中s0的value属性
        alterStr(s1, new char[]{'g', 'h', 'i'}); // 修改常量池中s1的value属性

        // 综上，此时常量池：entrySet[0]={hash:abc, k:s0(def)} entrySet[1]={hash:def, k:s1(ghi)} entrySet[2]={hash:ghi, k:s2(ghi)}

        String s4 = "abc"; // 在编译期间通过字节码指令ldc直接引用s0，并没有查询常量池（此时查不到，因为"abc".equals(s0) == false）
        String s5 = s0.intern(); // 查询常量池：s0.equals(s0) == true；查找到s0(def)返回
        String s6 = s1.intern(); // 查询常量池：s1.equals(s2) == true；查找到s2(ghi)返回，这里s2排在s1前面（头插法）

        String s7 = new String(s4.getBytes()); // 查询常量池：根据hash(def)找到s1(ghi)，判断def.equals(ghi) == false，查询失败，重新插入def常量 entrySet[3]={hash:def, k:s7(def)}
        String s8 = new String(s4.getBytes()); // 查询常量池：根据hash(def)找到s7(def)，返回s7
        alterStr(s7, new char[]{'j', 'k', 'l'});

        System.out.println("s = " + s); // s0 = def，证明修改s0成功
        System.out.println("s0 = " + s0); // s0 = def，证明修改s0成功
        System.out.println("s1 = " + s1); // s1 = ghi，证明修改s1成功
        System.out.println("s2 = " + s2); // s2 = ghi，证明修改s2成功
        System.out.println("s3 = " + s3); // s3 = def
        System.out.println("s4 = " + s4); // s4 = def
        System.out.println("s5 = " + s5); // s5 = def
        System.out.println("s6 = " + s6); // s6 = ghi
        System.out.println("s7 = " + s7); // s7 = jkl
        System.out.println("s8 = " + s8); // s8 = jkl

        System.out.println("(s3 != s0 && s3.equals(s0)) = " + (s3 != s0 && s3.equals(s0))); // true，证明修改s0后同样会修改s3，s0和s3实际共用kalue属性
        System.out.println("(s4 == s0 && s5 == s0) = " + (s4 == s0 && s5 == s0)); // true，证明s4和s5都复制于s0
        System.out.println("(s6 == s2 && s6 != s1) = " + (s6 == s2 && s6 != s1)); // true，证明s6复制于s2而不是s1
        System.out.println("(s7 != s4 && s7.equals(s8)) = " + (s7 != s4 && s7.equals(s8))); // true，证明s6复制于s2而不是s1

        // 总结：new String()、"xxx"、s.intern()这三种创建字符串的方式都会先去常量池中查找，匹配成功就直接引用，失败就创建

    }

    public static void alterStr(String s, char[] chars) {
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            char[] sChars = (char[]) field.get(s);
            System.arraycopy(chars, 0, sChars, 0, sChars.length);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean equalsStr(String s, String t) {
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            return field.get(s) == field.get(t);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
