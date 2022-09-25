package ru.mab.siaod.graph;

public class UnidirectedMapGraph<Key extends Comparable<Key>, Node extends GraphNode<Key>>
        extends DirectedMapGraph<Key, Node> {
    @Override
    public void addEdge(Key k1, Key k2) {
        edges.get(k1).add(getNode(k2));
        edges.get(k2).add(getNode(k1));
    }
}
