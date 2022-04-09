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
        Node node46 = node(46);
        Node node35 = node(35);
        Node node26 = node(26, List.of(
                node35
        ));
        node26.mark = true;
        Node root = node(-1, asList(
                node(23),
                node(7),
                node(21),
                min,
                node(17, List.of(
                        node(30))),
                node(24, asList(
                        node26,
                        node46
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

        heap.decreaseKey(node46, 15);
        System.out.println("After decrease 46 to 15: ");
        print(heap);

        heap.decreaseKey(node35, 5);
        System.out.println("After decrease 35 to 5: ");
        print(heap);
    }

    private static Node node(int key, List<Node> children) {
        Node root = new Node(key);
        int size = children.size();
        int maxDegree = 0;
        for (int i = 0; i < size; i++) {
            Node child = children.get(i);
            Node prev = children.get((i + size - 1) % size);
            Node next = children.get((i + 1) % size);

            child.left = prev;
            child.right = next;
            prev.right = child;
            next.left = child;

            child.p = root;
            maxDegree = Math.max(maxDegree, child.degree + 1);
        }

        root.degree = maxDegree;
        root.child = children.get(0);
        return root;
    }

    private static Node node(int key) {
        return new Node(key);
    }

    private static void print(FibonacciHeap heap) {
        System.out.println("-----------------------");
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
            System.out.println(prefix + "v: " + cur.key + " | d: " + cur.degree + " | m: " + cur.mark);
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

    private void addNodeAfter(Node x, Node y) {
        Node right = y.right;
        right.left = x;
        x.right = right;
        y.right = x;
        x.left = y;
    }

    private void addChild(Node x, Node parent) {
        Node firstChild = parent.child;
        if (firstChild == null) {
            parent.child = x;
            parent.degree++;
        } else {
            addNodeBefore(x, firstChild);
            parent.child = x;
            parent.degree = Math.max(1 + x.degree, parent.degree);
        }
    }

    void decreaseKey(Node x, int k) {
        if (k > x.key) {
            throw new IllegalArgumentException("New key[" + k + "] is more then current[" + x.key + "]");
        }

        x.key = k;
        Node y = x.p;
        if (y != null && x.key < y.key) {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.key < min.key) {
            min = x;
        }
    }

    private void cascadingCut(Node y) {
        Node z = y.p;
        if (z != null) {
            if (!y.mark) {
                y.mark = true;
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    private void cut(Node x, Node y) {
        removeFromParent(x);
        addNodeBefore(x, min);
        x.p = null;
        x.mark = false;
    }

    Node minimum() {
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

            removeFromParent(z);

            if (z == z.right) {
                min = null;
            } else {
                min = right;
                print(this);
                consolidate();
            }

            n--;
        }

        return z;
    }

    private void consolidate() {
        Node[] temp = new Node[100];

        Node last = min.left;
        Node cur = min;
        boolean isEnd = false;
        while (!isEnd) {
            Node x = cur;
            int d = x.degree;

            while (temp[d] != null) {
                Node y = temp[d];
                if (x.key > y.key) {
                    int tempKey = x.key;
                    x.key = y.key;
                    y.key = tempKey;
                }

                heapLink(y, x);
                temp[d] = null;
                d++;
            }

            temp[d] = x;

            isEnd = cur == last;
            cur = cur.right;
        }

        min = null;
        for (Node node : temp) {
            if (node != null) {
                if (min == null) {
                    min = node;
                    min.left = min.right = node;
                } else {
                    addNodeBefore(node, min);
                    if (node.key < min.key) {
                        min = node;
                    }
                }
            }
        }
    }

    private void heapLink(Node y, Node x) {
        removeFromParent(y);
        y.p = x;
        y.mark = false;
        y.left = y.right = y;
        addChild(y, x);
    }

    private void removeFromParent(Node z) {
        z.left.right = z.right;
        z.right.left = z.left;

        if (z.p != null) {
            if (z == z.right) {
                z.p.child = null;
            } else {
                z.p.child = z.right;
            }

            z.p.degree--;
        }
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
