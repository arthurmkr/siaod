package ru.mab.siaod.graph;

import java.util.HashMap;
import java.util.Map;

public class DFS {
    <K extends Comparable<K>, N extends GraphNode<K>> Map<K, DFSNode<K, N>> dfs(Graph<K, N> graph) {
        Map<K, DFSNode<K, N>> dfsNodesMap = new HashMap<>();
        int time = 0;

        for (N node : graph.getNodes()) {
            DFSNode<K, N> dfsNode = dfsNodesMap.get(node.getKey());
            if (dfsNode == null) {
                dfsNode = new DFSNode<>(null, node);
                dfsNodesMap.put(node.getKey(), dfsNode);
                time = visit(graph, node, dfsNode, dfsNodesMap, time);
            }
        }
        return dfsNodesMap;
    }

    private <K extends Comparable<K>, N extends GraphNode<K>> int visit(Graph<K, N> graph,
                                                                        N node,
                                                                        DFSNode<K, N> dfsNode,
                                                                        Map<K, DFSNode<K, N>> dfsNodesMap,
                                                                        int time) {
        time++;
        dfsNode.discoveryTime = time;

        for (N related : graph.getNodes(node)) {
            DFSNode<K, N> relatedDfsNode = dfsNodesMap.get(related.getKey());
            if (relatedDfsNode == null) {
                relatedDfsNode = new DFSNode<>(dfsNode, related);
                dfsNodesMap.put(related.getKey(), relatedDfsNode);

                time = visit(graph, related, relatedDfsNode, dfsNodesMap, time);
            }
        }
        time++;
        dfsNode.finishingTime = time;
        return time;
    }

    static class DFSNode<K extends Comparable<K>, N extends GraphNode<K>> {
        DFSNode<K, N> parent;
        N node;
        /**
         * Время обнаружения вершины
         */
        int discoveryTime;
        /**
         * Время завершения обработки вершины
         */
        int finishingTime;

        public DFSNode(DFSNode<K, N> p, N node) {
            this.parent = p;
            this.node = node;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("[node: ").append(node.getKey())
                    .append(", discoveryTime: ").append(discoveryTime)
                    .append(", finishingTime: ").append(finishingTime)
                    .append(", parent: ").append(parent)
                    .append("]").toString();
        }
    }
}
