package ru.mab.siaod.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.mab.siaod.RandomUtil.nextInt;
import static ru.mab.siaod.tree.RBTree.Color.BLACK;
import static ru.mab.siaod.tree.RBTree.Color.RED;

public class RBTree {
    Node NIL = new Node(0, BLACK);
    Node root = NIL;

    public static void main(String[] args) {
        RBTree tree = new RBTree();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int v = nextInt(1000);
            list.add(v);
            tree.insert(v);
        }


        System.out.println(tree.asString());
        System.out.println("Min height: " + tree.getMinHeight());
        System.out.println("Max height: " + tree.getMaxHeight());

        for (int v : list) {
            tree.delete(v);
        }
        System.out.println(tree.asString());

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
        Node y = NIL;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (z.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.p = y;

        if (y == NIL) {
            root = z;
        } else if (z.value < y.value) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = NIL;
        z.right = NIL;
        z.color = RED;
        insertFixup(z);
    }

    private void insertFixup(Node z) {
        while (z.p.color == RED) {
            Node grandparent = z.p.p;

            if (z.p == grandparent.left) {
                Node uncle = grandparent.right;

                if (uncle.color == RED) {
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

                if (uncle.color == RED) {
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
        x.right = y.left;

        if (y.left != NIL) {
            y.left.p = x;
        }

        y.p = x.p;
        if (x.p == NIL) {
            root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }

        y.left = x;
        x.p = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;

        if (y.right != NIL) {
            y.right.p = x;
        }

        y.p = x.p;
        if (x.p == NIL) {
            root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }

        y.right = x;
        x.p = y;
    }

    private void transplant(Node u, Node v) {
        if (u.p == NIL) {
            root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }

        v.p = u.p;
    }

    private Node findNode(int v) {
        Node cur = root;

        while (cur != NIL && cur.value != v) {
            cur = cur.value > v ? cur.left : cur.right;
        }

        return cur;
    }

    public void delete(int v) {
        Node z = findNode(v);

        if (z == NIL) {
            return;
        }

        Node y = z;
        Node x;

        Color yOrigColor = y.color;

        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = findMinNode(z.right);
            yOrigColor = y.color;
            x = y.right;

            if (y.p == z) {
                x.p = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }

        if (yOrigColor == BLACK) {
            rbDeleteFixup(x);
        }
    }

    private void rbDeleteFixup(Node x) {
        Node w;
        while (x != root && x.color == BLACK) {
            if (x == x.p.left) {
                w = x.p.right;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.p.color = RED;
                    leftRotate(x.p);
                    w = x.p.right;
                }

                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.p;
                } else {
                    if (w.right.color == BLACK) {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.p.right;
                    }

                    w.color = x.p.color;
                    x.p.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.p);
                    x = root;
                }
            } else {
                w = x.p.left;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.p.color = RED;
                    rightRotate(x.p);
                    w = x.p.left;
                }

                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED;
                    x = x.p;
                } else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.p.left;
                    }

                    w.color = x.p.color;
                    x.p.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.p);
                    x = root;
                }
            }
        }

        x.color = BLACK;
    }

    private Node findMinNode(Node node) {
        Node min = node;
        while (min != NIL && min.left != NIL) {
            min = min.left;
        }
        return min;
    }

    public String asString() {
        StringBuilder builder = new StringBuilder();
        print("Tree: ", root, 0, builder);

        return builder.toString();
    }

    private void print(String prefix, Node node, int level, StringBuilder builder) {
        builder.append("\n");
        char[] indent = new char[level];
        Arrays.fill(indent, '\t');
        builder.append(indent).append(prefix);

        if (node != NIL) {
            builder.append("v: ").append(node.value)
                    .append(", color: ").append(node.color);

            print("l: ", node.left, level + 1, builder);
            print("r: ", node.right, level + 1, builder);
        } else {
            builder.append("NIL");
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

        public Node(int value, Color color) {
            this.value = value;
            this.color = color;
        }
    }
}
