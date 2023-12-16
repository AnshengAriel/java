package com.ariel.java.base.designmode;

public class Vehicle {
    private String name;
    public Vehicle(String name) {
        this.name = name;
    }
    public void run() {
        System.out.println(name);
    }
}
