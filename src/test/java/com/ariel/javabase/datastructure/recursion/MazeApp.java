package com.ariel.javabase.datastructure.recursion;

import org.junit.Test;

public class MazeApp {

    @Test
    public void test() {
        Maze maze = new Maze();
        maze.setWay(3, 1);
        maze.print();
    }
}
