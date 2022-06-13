package ru.mab.siaod.graph;

import org.apache.commons.lang3.RandomUtils;

public class GraphFactory {
    public static UndirectedGraph<Long, SimpleGraphNode<Long>> random(int v, int e) {
        UndirectedMapGraph<Long, SimpleGraphNode<Long>> graph = new UndirectedMapGraph<>();

        for (long i = 0; i < v; i++) {
            graph.addNode(new SimpleGraphNode<>(i));
        }

        for (int i = 0; i < e; i++) {
            graph.addEdge(RandomUtils.nextLong(0, v), RandomUtils.nextLong(0, v));
        }

        return graph;
    }
}
