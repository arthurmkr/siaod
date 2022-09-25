package ru.mab.siaod.graph;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TopologicalSortTest {
    @Test
    void sort() {
        Graph<Character, SimpleGraphNode<Character>> graph = new DirectedMapGraph<>();

        for (char c = 'm'; c <= 'z'; c++) {
            graph.addNode(new SimpleGraphNode<>(c));
        }

        Map<Character, Set<Character>> edges = new HashMap<>();
        edges.put('m', Set.of('r', 'q', 'x'));
        edges.put('n', Set.of('q', 'u', 'o'));
        edges.put('o', Set.of('r', 'v', 's'));
        edges.put('p', Set.of('o', 's', 'z'));
        edges.put('q', Set.of('t'));
        edges.put('r', Set.of('u', 'y'));
        edges.put('s', Set.of('r'));
        edges.put('t', Set.of());
        edges.put('u', Set.of('t'));
        edges.put('v', Set.of('x', 'w'));
        edges.put('w', Set.of('z'));
        edges.put('x', Set.of());
        edges.put('y', Set.of('v'));
        edges.put('z', Set.of());

        graph.addEdges(edges);

        System.out.println(graph);

        TopologicalSort algo = new TopologicalSort();
        List<SimpleGraphNode<Character>> res = algo.sort(graph);
        System.out.println(res);
    }
}
