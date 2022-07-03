package ru.mab.siaod.graph;

interface Graph<Key extends Comparable<Key>, Node extends GraphNode<Key>> {
    void addNode(Node n);

    void removeNode(Node n);

    Node getNode(Key k);

    void addEdge(Key k1, Key k2);

    void removeEdge(Key k1, Key k2);

    boolean hasEdge(Node n1, Node n2);

    Iterable<Node> getNodes();

    Iterable<Node> getNodes(Node curNode);
}
