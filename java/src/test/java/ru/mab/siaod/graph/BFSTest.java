package ru.mab.siaod.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    void bfs() {
        UndirectedGraph<Long, SimpleGraphNode<Long>> graph = GraphFactory.random(10, 10);
        BFS bfs = new BFS();
//        bfs.bfs()

        System.out.println(graph);
    }
}
