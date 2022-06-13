package ru.mab.siaod.graph;

import lombok.Value;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFS {
    <K extends Comparable<K>, N extends GraphNode<K>> BFSNode<K, N> bfs(UndirectedGraph<K, N> graph, K key) {
        Map<K, BFSNode<K, N>> bfsNodes = new HashMap<>();
        Queue<N> queue = new LinkedList<>();

        N firstNode = graph.getNode(key);
        queue.add(firstNode);
        BFSNode<K, N> root = new BFSNode<>(0, null, firstNode);
        bfsNodes.put(key, root);

        while (!queue.isEmpty()) {
            N curNode = queue.poll();
            BFSNode<K, N> curExtraData = bfsNodes.get(curNode.getKey());

            Iterable<N> related = graph.getNodes(curNode);
            for (N node : related) {
                BFSNode<K, N> bfsNode = bfsNodes.get(node.getKey());
                if (bfsNode == null) {
                    bfsNodes.put(node.getKey(), new BFSNode<K, N>(curExtraData.getD() + 1, curExtraData, curNode));

                    queue.add(node);
                }
            }
        }

        return root;
    }

    @Value
    static class BFSNode<K extends Comparable<K>, N extends GraphNode<K>> {
        int d;
        BFSNode<K, N> p;
        N node;
    }
}
