package com.ariel.java.base.datastructure.graph;

import org.junit.Test;

import static com.ariel.javabase.datastructure.graph.Graph.VERTEX.*;


public class GraphApi {

    @Test
    public void insert() {
        Graph graph = new Graph();
        graph.insert(A, B, 80);
        graph.insert(C, D, 80);
        graph.insert(E, B, 80);
        System.out.println(graph);
    }

    @Test
    public void depthFirst() {
        Graph graph = new Graph();
        graph.insert(A, B, 80);
        graph.insert(C, B, 80);
        graph.insert(B, D, 80);
        graph.insert(E, B, 80);
        System.out.println(graph);
        graph.depthFirst(A);
    }

    @Test
    public void boardFirst() {
        Graph graph = new Graph();
        graph.insert(A, B, 80);
        graph.insert(C, B, 80);
        graph.insert(B, D, 80);
        graph.insert(E, B, 80);
        System.out.println(graph);
        graph.boardFirst(A);
    }

}
