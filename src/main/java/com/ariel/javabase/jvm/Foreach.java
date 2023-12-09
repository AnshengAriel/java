package com.ariel.javabase.jvm;

import java.util.HashMap;

public class Foreach {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        for (String s : map.keySet()) {
            System.out.println(s);
        }
    }

}
