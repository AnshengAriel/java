package com.ariel.javabase.datastructure.hash;

public class EmpHashTable {

    private EmpLinkedList[] emps;

    public EmpHashTable(int size) {
        emps = new EmpLinkedList[size];
        for (int i = 0; i < emps.length; i++) {
            emps[i] = new EmpLinkedList();
        }
    }

    public void put(Emp emp) {
        emps[getHashcode(emp.getId())].add(emp);
    }

    private int getHashcode(int id) {
        return id % emps.length;
    }

    public Emp get(int id) {
        return emps[getHashcode(id)].get(id);
    }

    public void print() {
        for (int i = 0; i < emps.length; i++) {
            System.out.println(i + " " + emps[i]);
        }
    }

    public void delete(int id) {
        emps[getHashcode(id)].delete(id);
    }

}
