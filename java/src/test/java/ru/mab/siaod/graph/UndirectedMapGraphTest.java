package ru.mab.siaod.graph;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;


class UndirectedMapGraphTest {
    @Test
    void test() {
        UndirectedMapGraph<Long, SimpleGraphNode<Long>> graph = new UndirectedMapGraph<>();

        long V = 10;
        for (long i = 0; i < V; i++) {
            graph.addNode(new SimpleGraphNode<>(i));
        }

        for(int i = 0; i < 20; i++) {
            graph.addEdge(RandomUtils.nextLong(0, V), RandomUtils.nextLong(0, V));
        }

        System.out.println(graph);
    }
}