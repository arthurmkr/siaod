package ru.mab.siaod.graph;

import org.junit.jupiter.api.Test;

class BFSTest {

    @Test
    void bfs() {
        Graph<Long, SimpleGraphNode<Long>> graph = GraphFactory.random(10, 10);
        BFS bfs = new BFS();
//        bfs.bfs()
//
        System.out.println(graph);
    }
}
