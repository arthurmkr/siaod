package ru.mab.siaod.graph;

import lombok.Value;

@Value
public class SimpleGraphNode<Key extends Comparable<Key>> implements GraphNode<Key> {
    Key key;


    @Override
    public String toString() {
        return "SimpleGraphNode{" +
                "key=" + key +
                "}\n";
    }

    @Override
    public int compareTo(GraphNode<Key> o) {
        return o == null ? 1 : key.compareTo(o.getKey());
    }
}
