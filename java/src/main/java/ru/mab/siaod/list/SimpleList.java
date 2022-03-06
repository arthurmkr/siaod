package ru.mab.siaod.list;

import lombok.Getter;

public class SimpleList {
    private Node head;

    public void insertFirst(Node node) {
        node.next = head;
        head = node;
    }

    public static class Node {
        @Getter
        private final int v;
        private Node next;

        public Node(int v) {
            this.v = v;
        }
    }
}
