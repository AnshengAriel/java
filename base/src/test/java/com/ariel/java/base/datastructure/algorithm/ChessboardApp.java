package com.ariel.java.base.datastructure.algorithm;

import org.junit.Test;

public class ChessboardApp {

    @Test
    public void handle() {
        Chessboard chessboard = new Chessboard(8, 8);
        long t = System.currentTimeMillis();
        chessboard.handle(0, 0, 1);
        System.out.println(chessboard);
        System.out.println(System.currentTimeMillis() - t);
    }

}
