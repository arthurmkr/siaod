package ru.mab.siaod.tree;

import static ru.mab.siaod.tree.RBTree.Color.BLACK;
import static ru.mab.siaod.tree.RBTree.Color.RED;

public class RBTree {
    Node root;

    private static boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    public void insert(Node z) {
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.p = y;

        if (y == null) {
            root = z;
        } else if (z.key < y.key) {
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

    protected enum Color {
        RED, BLACK
    }

    private class Node {
        Node p;
        Node right;
        Node left;
        Color color;
        int key;
    }
}
