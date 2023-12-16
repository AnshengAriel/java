package com.ariel.java.base.datastructure.recursion;

import java.util.Arrays;

/**
 * 迷宫问题
 */
public class Maze {

    private int[][] maze;

    public Maze() {
        maze = new int[8][7];
        Arrays.fill(maze[0], 1);
        Arrays.fill(maze[7], 1);
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        maze[4][1] = 1;
        maze[4][2] = 1;
    }

    public boolean setWay(int i, int j) {
        // 终点已走通
        if (maze[6][5] == 2) {
            return true;
        }
        switch (maze[i][j]) {
            // 这个可以走
            case 0:
                maze[i][j] = 2;
                // true=这个点的下右上左四个点有一个能走通；false=走不通就标记为3
                if (setWay(i + 1, j) || setWay(i, j + 1) || setWay(i - 1, j) || setWay(i, j -1)) {
                    return true;
                }else {
                    maze[i][j] = 3;
                    return false;
                }
            // 是墙，走不通
            case 1:
            // 已走过，不能往回走
            case 2:
            // 之前走过，但走不通
            case 3:
                return false;
            default:
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

}
