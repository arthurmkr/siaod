package ru.mab.siaod.graph;

import org.junit.jupiter.api.Test;

import java.util.Map;

class DFSTest {

    @Test
    void bfs() {
        Graph<Integer, SimpleGraphNode<Integer>> graph = new DirectedMapGraph<>();

        for(int i = 0; i < 6; i++) {
            graph.addNode(new SimpleGraphNode<>(i));
        }

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 1);
        graph.addEdge(4, 3);
        graph.addEdge(5, 5);

        System.out.println(graph);

        DFS algo = new DFS();
        Map<Integer, DFS.DFSNode<Integer, SimpleGraphNode<Integer>>> res = algo.dfs(graph);
        res.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
//        System.out.println(res);
    }
}
