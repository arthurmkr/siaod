package ru.mab.siaod.tree;

import java.util.Arrays;

public class Tree {
    Node root;

    public void insert(int v) {
        Node newNode = new Node(v);

        if (root == null) {
            root = newNode;
            return;
        }

        Node cur = root;
        Node parent = null;
        while (cur != null) {
            parent = cur;
            if (cur.value > v) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        if (parent.value > v) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        print("Tree: ", root, 0, builder);

        return builder.toString();
    }

    private void print(String prefix, Node node, int level, StringBuilder builder) {
        if (node != null) {
            builder.append("\n");
            char[] indent = new char[level];
            Arrays.fill(indent, '\t');
            builder.append(indent).append(prefix);
            builder.append(node.value);

            print("l: ", node.left, level + 1, builder);
            print("r: ", node.right, level + 1, builder);
        }
    }

    public class Node {
        private final int value;
        private Node left;
        private Node right;
//        private Node parent;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
