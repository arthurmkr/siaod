package ru.mab.siaod.tree;

import lombok.Value;

public class BTree {
    private int degree;
    private Node root;

    public BTree(int degree) {
        this.degree = degree;

        root = new Node();
        diskWrite(root);
    }

    Pair search(Node x, long key) {
        int i = 0;
        while (i < x.size && key > x.keys[i]) {
            i++;
        }

        if (i < x.size && key == x.keys[i]) {
            return new Pair(x, i);
        } else if (x.leaf) {
            return null;
        } else {
            return search(x.children[i], key);
        }
    }

    void split(Node x, int index) {
        Node z = new Node();
        Node y = x.children[index];
        z.leaf = y.leaf;
        z.size = degree - 1;

        // todo заменить на копирование массива
        for (int j = 0; j < z.size; j++) {
            z.keys[j] = y.keys[j + degree];
        }

        if (!y.leaf) {
            // todo заменить на копирование массива
            for (int j = 0; j < degree; j++) {
                z.children[j] = y.children[j + degree];
            }
        }

        y.size = degree - 1;
        int j;
        // todo заменить на копирование массива
        for (j = x.size; j >= index; j--) {
            x.children[j + 1] = x.children[j];
        }

        x.children[j + 1] = z;
        // todo заменить на копирование массива
        for (int k = x.size; k > index; k++) {
            x.keys[k + 1] = x.keys[k];
        }
        x.keys[index] = y.keys[degree - 1];
        x.size++;

        diskWrite(y);
        diskWrite(z);
        diskWrite(x);
    }

    void insert(int k) {
        Node r = root;
        if (r.size == 2 * degree - 1) {
            Node s = new Node();
            root = s;
            s.children[0] = r;

            split(s, 0);
            insertNonFull(s, k);
        } else {
            insertNonFull(r, k);
        }
    }

    private void insertNonFull(Node x, int k) {
        int i = x.size - 1;
        if (x.leaf) {
            // todo заменить на бинарный поиск + копирование массива
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }

            x.keys[i + 1] = k;
            x.size++;
            diskWrite(x);
        } else {
            // todo заменить на бинарный поиск
            while (i >= 0 && k < x.keys[i]) {
                i--;
            }
            i++;
            diskRead(x.children[i]);

            if (x.children[i].size == 2 * degree - 1) {
                split(x, i);
                if (k > x.keys[i]) {
                    i++;
                }

                insertNonFull(x.children[i], k);
            }
        }
    }

    private void diskRead(Node node) {

    }

    private void diskWrite(Node node) {

    }

    @Value
    private class Pair {
        Node node;
        int index;
    }

    private class Node {
        boolean leaf;
        int size;
        Node[] children = new Node[2 * degree];
        long[] keys = new long[2 * degree - 1];
    }
}
