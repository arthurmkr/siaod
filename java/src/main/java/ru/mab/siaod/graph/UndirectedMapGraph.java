package ru.mab.siaod.graph;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@ToString
public class UndirectedMapGraph<Key extends Comparable<Key>, Node extends GraphNode<Key>> implements UndirectedGraph<Key, Node> {
    private final Set<Edge> edges = new HashSet<>();
    private final Map<Key, Node> nodes = new TreeMap<>();

    @Override
    public void addNode(Node node) {
        nodes.put(node.getKey(), node);
    }

    @Override
    public void removeNode(Node n) {
        for (Key key : nodes.keySet()) {
            removeEdge(key, n.getKey());
        }
    }

    @Override
    public Node getNode(Key key) {
        return nodes.get(key);
    }

    @Override
    public void addEdge(Key k1, Key k2) {
        edges.add(new Edge(k1, k2));
    }

    @Override
    public void removeEdge(Key k1, Key k2) {
        edges.remove(new Edge(k1, k2));
    }

    @Override
    public boolean hasEdge(Node n1, Node n2) {
        return edges.contains(new Edge(n1.getKey(), n2.getKey()));
    }

    @Override
    public String toString() {
        return "UndirectedMapGraph{" +
                "edges=" + edges +
                ",\nnodes=" + nodes +
                '}';
    }

    @Value
    @EqualsAndHashCode
    class Edge {
        Key n1;
        Key n2;

        @Override
        public String toString() {
            return "\nE{" +
                    "" + n1 +
                    " -- " + n2 +
                    '}';
        }
    }
}
