package ru.mab.siaod.tree;

import java.util.Arrays;

import static ru.mab.siaod.tree.RBTree.Color.BLACK;
import static ru.mab.siaod.tree.RBTree.Color.RED;

public class RBTree {
    Node root;

    private static boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    public static void main(String[] args) {
        RBTree tree = new RBTree();
        for (int i = 0; i < 10; i++) {
//            tree.insert(i);
            tree.insert((int) (Math.random() * 1000));
        }

        System.out.println("Min height: " + tree.getMinHeight());
        System.out.println("Max height: " + tree.getMaxHeight());
        System.out.println(tree);
    }

    public int getMinHeight() {
        return getMinHeight(root);
    }

    public int getMaxHeight() {
        return getMaxHeight(root);
    }

    private int getMinHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getMinHeight(node.left);
        int rightHeight = getMinHeight(node.right);

        return Math.min(leftHeight, rightHeight) + 1;
    }

    private int getMaxHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getMaxHeight(node.left);
        int rightHeight = getMaxHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void insert(int value) {
        insert(new Node(value));
    }

    public void insert(Node z) {
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (z.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.p = y;

        if (y == null) {
            root = z;
        } else if (z.value < y.value) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.color = RED;
        insertFixup(z);
    }

    private void insertFixup(Node z) {
        while (isRed(z.p)) {
            Node grandparent = z.p.p;

            if (z.p == grandparent.left) {
                Node uncle = grandparent.right;

                if (isRed(uncle)) {
                    z.p.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    z = grandparent;
                } else {
                    if (z == z.p.right) {
                        z = z.p;
                        leftRotate(z);
                    }

                    z.p.color = BLACK;
                    grandparent.color = RED;
                    rightRotate(grandparent);
                }
            } else {
                Node uncle = grandparent.left;

                if (isRed(uncle)) {
                    z.p.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    z = grandparent;
                } else {
                    if (z == z.p.left) {
                        z = z.p;
                        rightRotate(z);
                    }

                    z.p.color = BLACK;
                    grandparent.color = RED;
                    leftRotate(grandparent);
                }
            }
        }

        root.color = BLACK;
    }

    private void leftRotate(Node x) {
        Node y = x.right;

        if (y.left != null) {
            y.left.p = x;
        }

        x.right = y.left;
        y.left = x;

        changeParent(x, y);
    }

    private void rightRotate(Node x) {
        Node y = x.left;

        if (y.right != null) {
            y.right.p = x;
        }

        x.left = y.right;
        y.right = x;

        changeParent(x, y);
    }

    private void changeParent(Node x, Node y) {
        if (x.p == null) {
            root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }

        y.p = x.p;
        x.p = y;
    }

    private void transplant(Node u, Node v) {
        if (u.p == null) {
            root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            u.p.right = v;
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
            builder.append("v: ").append(node.value)
                    .append(", color: ").append(node.color);

            print("l: ", node.left, level + 1, builder);
            print("r: ", node.right, level + 1, builder);
        }
    }

    protected enum Color {
        RED, BLACK
    }

    private class Node {
        Node p;
        Node right;
        Node left;
        Color color;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
