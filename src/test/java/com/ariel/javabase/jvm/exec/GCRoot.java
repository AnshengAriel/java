package com.ariel.javabase.jvm.exec;

import java.util.ArrayList;
import java.util.Scanner;

public class GCRoot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("add：添加数据\ndelete：删除数据\ngc：回收数据\n任意字符结束");

        ArrayList<Integer> list;
        while (true) {
            switch (scanner.next()) {
                case "add":
                    list = new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        list.add(i);
                    }
                    break;
                case "delete":
                    list = null;
                    break;
                case "gc":
                    System.gc();
                    break;
                default:
                    scanner.close();
                    return;
            }
        }

    }

}
