package com.ariel.javabase.datastructure.dynamic;

import org.junit.Test;

public class BagApp {

    @Test
    public void recurse() {
        int capacity = 4;
        Bag bag = new Bag();
        int maxPrice = bag.recurse(capacity);
        System.out.printf("背包总容量[%d] maxPrice[%d]%n", capacity, maxPrice);
        bag.printItem();
    }

    @Test
    public void loop() {
        Bag bag = new Bag();
        bag.loop();
    }

}
