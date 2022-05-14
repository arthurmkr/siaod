package ru.mab.siaod.graph;

interface UndirectedGraph<Key extends Comparable<Key>, Node extends GraphNode<Key>> {
    void addNode(Node n);

    void removeNode(Node n);

    GraphNode<Key> getNode(Key k);

    void addEdge(Key k1, Key k2);

    void removeEdge(Key k1, Key k2);

    boolean hasEdge(Node n1, Node n2);
}
