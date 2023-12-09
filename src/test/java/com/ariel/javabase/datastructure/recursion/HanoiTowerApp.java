package com.ariel.javabase.datastructure.recursion;

import org.junit.Test;

public class HanoiTowerApp {

    @Test
    public void loop() {
        HanoiTower hanoiTower = new HanoiTower();
        hanoiTower.recurse(5, 'A', 'B', 'C');
    }

}
