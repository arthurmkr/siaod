package ru.mab.siaod.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TopologicalSort {
    <K extends Comparable<K>, N extends GraphNode<K>> List<N> sort(Graph<K, N> graph) {
        Set<K> processedKeys = new HashSet<>();
        List<N> res = new LinkedList<>();

        for (N node : graph.getNodes()) {
            if (!processedKeys.contains(node.getKey())) {
                processedKeys.add(node.getKey());
                visit(graph, node, processedKeys, res);
                res.add(0, node);
            }
        }
        return res;
    }

    private <K extends Comparable<K>, N extends GraphNode<K>> void visit(Graph<K, N> graph,
                                                                         N node,
                                                                         Set<K> processedKeys,
                                                                         List<N> result) {

        for (N related : graph.getNodes(node)) {
            if (!processedKeys.contains(related.getKey())) {
                processedKeys.add(related.getKey());
                visit(graph, related, processedKeys, result);
                result.add(0, related);
            }
        }
    }
}
