package ru.mab.siaod.graph;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

public class DFS {
    <K extends Comparable<K>, N extends GraphNode<K>> DFSNode<K, N> dfs(Graph<K, N> graph, N node) {
        return visit(graph, new HashMap<>(), node, null);
    }

    private <K extends Comparable<K>, N extends GraphNode<K>> DFSNode<K, N> visit(Graph<K, N> graph,
                                                                                  Map<K, DFSNode<K, N>> dfsNodes,
                                                                                  N node,
                                                                                  DFSNode<K, N> parentDfsNode) {
        DFSNode<K, N> dfsNode = new DFSNode<>(parentDfsNode, node);
        dfsNodes.put(node.getKey(), dfsNode);

        for (N child : graph.getNodes(node)) {
            if (!dfsNodes.containsKey(child.getKey())) {
                visit(graph, dfsNodes, child, dfsNode);
            }
        }

        return dfsNode;
    }

    @Value
    static class DFSNode<K extends Comparable<K>, N extends GraphNode<K>> {
        DFSNode<K, N> p;
        N node;
    }
}
