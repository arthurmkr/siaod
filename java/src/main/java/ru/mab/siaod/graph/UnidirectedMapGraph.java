package ru.mab.siaod.graph;

import java.util.*;

public class UnidirectedMapGraph<Key extends Comparable<Key>, Node extends GraphNode<Key>> implements Graph<Key, Node> {
    private final Map<Key, SortedSet<Node>> edges = new TreeMap<>();
    private final Map<Key, Node> nodes = new TreeMap<>();

    @Override
    public void addNode(Node node) {
        nodes.put(node.getKey(), node);
        edges.put(node.getKey(), new TreeSet<>());
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
        edges.get(k1).add(getNode(k2));
        edges.get(k2).add(getNode(k1));
    }

    @Override
    public void removeEdge(Key k1, Key k2) {
        edges.get(k1).remove(getNode(k2));
        edges.get(k2).remove(getNode(k1));
    }

    @Override
    public boolean hasEdge(Node n1, Node n2) {
        return edges.get(n1.getKey()).contains(n2);
    }

    @Override
    public Iterable<Node> getNodes() {
        return nodes.values();
    }

    @Override
    public Iterable<Node> getNodes(Node node) {
        return edges.get(node.getKey());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Graph\n")
                .append("---------------------------------------------------\n");

        builder.append("Nodes: ");
        String nodeStrs = makeNodesList(nodes.values());

        builder.append(nodeStrs)
                .append("\n---------------------------------------------------\n")
                .append("Edges: \n");

        for(Key key : nodes.keySet()) {
            builder.append(key)
                    .append(":  ")
                    .append(makeNodesList(edges.get(key)))
                    .append("\n");
        }

        return builder.toString();
    }

    private String makeNodesList(Iterable<Node> nodesList) {
        List<String> nodeStrs = new ArrayList<>();
        for(Node node : nodesList) {
            nodeStrs.add(node.getKey().toString());
        }
        return String.join(", ", nodeStrs);
    }
}
