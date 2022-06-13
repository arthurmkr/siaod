package ru.mab.siaod.graph;

interface GraphNode<Key extends Comparable<Key>> extends Comparable<GraphNode<Key>> {
    Key getKey();
}
