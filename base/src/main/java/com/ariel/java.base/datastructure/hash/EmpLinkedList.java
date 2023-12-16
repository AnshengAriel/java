package com.ariel.java.base.datastructure.hash;

import java.util.StringJoiner;

public class EmpLinkedList {

    private Emp head;

    public void add(Emp emp) {
        Emp temp = this.head;
        if (temp == null) {
            this.head = emp;
        } else if (temp.getId() != emp.getId()) {
            while(true) {
                if (temp.next == null) {
                    temp.next = emp;
                    break;
                }
                if (temp.next.getId() == emp.getId()) {
                    break;
                }
                temp = temp.next;
            }
        }
    }

    public Emp get(int id) {
        for(Emp temp = this.head; temp != null; temp = temp.next) {
            if (temp.getId() == id) {
                return temp;
            }
        }
        return null;
    }

    public Emp delete(int id) {
        Emp temp = this.head;
        if (temp == null) {
            return null;
        } else if (temp.getId() == id) {
            this.head = null;
            return temp;
        } else {
            while(temp.next != null) {
                if (temp.next.getId() == id) {
                    Emp delete = temp.next;
                    temp.next = temp.next.next;
                    return delete;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    @Override
    public String toString() {
        Emp temp = this.head;
        StringJoiner joiner;
        for(joiner = new StringJoiner(", "); temp != null; temp = temp.next) {
            joiner.add(temp.toString());
        }
        return "[" + joiner + "]";
    }
}
