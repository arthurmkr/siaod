package ru.mab.siaod.tree;

public class TreeMain {
    public static Node node(int value, Node left, Node right) {
        return new Node(value, left, right);
    }

    public static Node node(int value) {
        return node(value, null, null);
    }

    public static void main(String[] args) {
        Node root =
                node(5,
                        node(3,
                                node(2),
                                node(4)),
                        node(7,
                                node(6),
                                node(8))
                );


        System.out.println(maxDepth(root));
    }

    public static int maxDepth(Node node) {
        if (node == null) {
            return -1;
        }

        return Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight())) + 1;
    }
}
