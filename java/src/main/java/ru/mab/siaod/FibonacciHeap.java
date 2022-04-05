package ru.mab.siaod;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Фибоначчиева пирамида
 */
public class FibonacciHeap {
    Node min;
    int n;

    public static void main(String[] args) {
        Node min = node(3, asList(
                node(18, List.of(
                        node(39))),
                node(52),
                node(38, List.of(
                        node(41))))
        );
        Node root = node(-1, asList(
                node(23),
                node(7),
                node(21),
                min,
                node(17, List.of(
                        node(30))),
                node(24, asList(
                        node(26, List.of(
                                node(35)
                        )),
                        node(46)
                ))
        ));

        FibonacciHeap heap = new FibonacciHeap();
        heap.min = min;

        System.out.println("After creating:");
        print(heap);

        Node node = heap.extractMin();

        System.out.println("Min: " + node.key);

        System.out.println("After extractMin: ");
        print(heap);

        heap.consolidate();
        System.out.println("After consolidate: ");
        print(heap);
    }

    private static Node node(int key, List<Node> children) {
        Node root = new Node(key);
        int size = children.size();
        int maxDegree = -1;
        for (int i = 0; i < size; i++) {
            Node child = children.get(i);
            Node prev = children.get((i + size - 1) % size);
            Node next = children.get((i + 1) % size);

            child.left = prev;
            child.right = next;
            prev.right = child;
            next.left = child;

            child.p = root;
            maxDegree = Math.max(child.degree, maxDegree);
        }

        root.degree = maxDegree + 1;
        root.child = children.get(0);
        return root;
    }

    private static Node node(int key) {
        return new Node(key);
    }

    private static void print(FibonacciHeap heap) {
        print(heap.min, "");
    }

    private static void print(Node node, String prefix) {
        if (node == null) {
            return;
        }

        boolean isEnd = false;
        Node last = node.left;
        Node cur = node;

        while (!isEnd) {
            System.out.println(prefix + "v: " + cur.key + " | d: " + cur.degree);
            print(cur.child, prefix + "      ");

            isEnd = cur == last;
            cur = cur.right;
        }
    }


    void insert(Node x) {
        x.degree = 0;
        x.p = null;
        x.child = null;
        x.mark = false;

        if (min == null) {
            min = x;
        } else {
            addNodeBefore(x, min);

            if (x.key < min.key) {
                min = x;
            }
        }

        n++;
    }

    private void addNodeBefore(Node x, Node y) {
        Node left = y.left;
        left.right = x;
        x.left = left;
        y.left = x;
        x.right = y;
    }

    private void addChild(Node x, Node parent) {
        Node firstChild = parent.child;
        if (firstChild == null) {
            parent.child = x;
        } else {
            addNodeBefore(x, firstChild);
        }
    }

    Node peekMin() {
        return min;
    }

    Node extractMin() {
        Node z = min;

        if (z != null) {
            Node right = z.right;

            Node curChild = z.child;
            while (curChild.p != null) {
                Node nextChild = curChild.right;
                addNodeBefore(curChild, z);
                curChild.p = null;
                curChild = nextChild;
            }

            deleteNode(z);

            if (z == z.right) {
                min = null;
            } else {
                min = right;
            }

            n--;
        }

        return z;
    }

    private void consolidate() {
        Node[] a = new Node[100];

        Node last = min.left;
        Node cur = min;
        boolean isEnd = false;
        while (!isEnd) {
            Node x = cur;
            int d = x.degree;

            while (a[d] != null) {
                Node y = a[d];
                if (x.key > y.key) {
                    int temp = x.key;
                    x.key = y.key;
                    y.key = temp;
                }

                heapLink(y, x);
                a[d] = null;
                d++;
            }

            a[d] = x;

            isEnd = cur == last;
            cur = cur.right;
        }
    }

    private void heapLink(Node y, Node x) {
        deleteNode(y);
        y.p = x;
        y.mark = false;
        y.left = y.right = null;
        addChild(y, x);
        x.degree++;
    }

    private void deleteNode(Node z) {
        z.left.right = z.right;
        z.right.left = z.left;
    }

    FibonacciHeap merge(FibonacciHeap h1, FibonacciHeap h2) {
        FibonacciHeap h = new FibonacciHeap();

        if (h1.min == null && h2.min == null) {
            return h;
        } else if (h1.min == null) {
            return h2;
        } else if (h2.min == null) {
            return h1;
        } else {
            h.min = h1.min.key < h2.min.key ? h2.min : h1.min;

            Node leftH1 = h1.min.left;
            leftH1.right = h2.min;
            Node leftH2 = h2.min.left;
            leftH2.right = h1.min;

            h.n = h1.n + h2.n;
        }

        return h;
    }

    static class Node {
        Node p;
        Node child;
        int key;

        Node left = this;
        Node right = this;
        int degree;
        boolean mark;

        public Node(int key) {
            this.key = key;
        }
    }
}
