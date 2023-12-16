package com.ariel.java.base.datastructure.hash;

import org.junit.Test;

public class EmpHashTableApp {

    @Test
    public void put() {
        EmpHashTable map = new EmpHashTable(10);
        for (int i = 0; i < 100; i++) {
            map.put(new Emp(i, "Hello " + i));
        }
        map.print();
    }

    @Test
    public void get() {
        EmpHashTable map = new EmpHashTable(10);
        for (int i = 0; i < 100; i++) {
            map.put(new Emp(i, "Hello " + i));
        }
        System.out.println("map.get(66) = " + map.get(66));
    }

    @Test
    public void delete() {
        EmpHashTable map = new EmpHashTable(10);
        for (int i = 0; i < 100; i++) {
            map.put(new Emp(i, "Hello " + i));
        }
        map.print();
        map.delete(66);
        System.out.println();
        map.print();
    }

}
