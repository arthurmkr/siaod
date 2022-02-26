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
            cur = cur.value > v ? cur.left : cur.right;
        }

        newNode.p = parent;
        if (parent.value > v) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public void delete(int v) {
        Node z = findNode(v);

        if (z == null) {
            return;
        }

        if (z.left == null) {
            transplant(z, z.right);
        } else if(z.right == null) {
            transplant(z, z.left);
        } else {
            Node minNode = findMinNode(z.right);
            if(minNode.p != z) {
                transplant(minNode, minNode.right);
            }

            transplant(z, minNode);
            minNode.left = z.left;
            minNode.left.p = minNode;
        }
    }

    private Node findNode(int v) {
        Node cur = root;

        while (cur != null && cur.value != v) {
            cur = cur.value > v ? cur.left : cur.right;
        }

        return cur;
    }

    private void transplant(Node u, Node v) {
        if (u.p == null) {
            root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }

        if (v != null) {
            v.p = u.p;
        }
    }

    private Node findMinNode(Node node) {
        Node min = node;
        while (min != null && min.left != null) {
            min = min.left;
        }
        return min;
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
        private Node p;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
