package com.ariel.java.base.collection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapApi {

    @Test
    public void test() throws IllegalAccessException {
        HashMap<Node, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(new Node(i), i);
        }
        System.out.println(map.get(new Node(70)));

    }

    public void printMap(HashMap<Node, Integer> map) throws IllegalAccessException {
        Set<Map.Entry<Node, Integer>> entries = map.entrySet();
        ArrayList<String> fieldNames = new ArrayList<>();
        fieldNames.add("key");
        fieldNames.add("left");
        fieldNames.add("right");

        ArrayList<Field> fields = new ArrayList<>();
        for (Map.Entry<Node, Integer> entry : entries) {
            if (fields.isEmpty()) {
                Class temp = entry.getClass();
                while (temp != null) {
                    for (Field field : temp.getDeclaredFields()) {
                        if (fieldNames.contains(field.getName())) {
                            field.setAccessible(true);
                            fields.add(field);
                        }
                    }
                    temp = temp.getSuperclass();
                }
            }
            StringBuilder builder = new StringBuilder();
            for (Field field : fields) {
                Object o = field.get(entry);
                builder.append(field.getName()).append("=[").append(o).append("] ");
            }
            System.out.println(builder);
        }
    }

    public static class Node {
        public int i;
        public Node(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return i%10;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node another = (Node) obj;
                return this == another || this.i == another.i;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.valueOf(i);
        }
    }

}
