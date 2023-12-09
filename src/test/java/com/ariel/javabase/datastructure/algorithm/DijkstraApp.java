package com.ariel.javabase.datastructure.algorithm;

import org.junit.Test;

import java.util.ArrayList;

public class DijkstraApp {

    @Test
    public void handle() {
        Dijkstra dijkstra = new Dijkstra();
        ArrayList<Dijkstra.Vertex> list = dijkstra.handle('D');
        for (Dijkstra.Vertex vertex : list) {
            System.out.println(vertex);
        }
    }

}
