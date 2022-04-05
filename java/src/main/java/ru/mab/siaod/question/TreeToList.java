package ru.mab.siaod.question;

import lombok.AllArgsConstructor;
import lombok.Value;

public class TreeToList {
    public static void main(String[] args) {
        Node root = new Node(20,
                new Node(5,
                        new Node(1, null, null),
                        null),
                new Node(30, null, null)
        );

        Node list = transform(root);
        // check
        int prev = 0;
        Node cur = list;
        while (cur != null) {
            if (cur.v < prev) {
                throw new IllegalStateException("error");
            }

            prev = cur.v;
            cur = cur.right;
        }

        System.out.println(prev);
    }

    private static Node transform(Node root) {
        if (root == null) {
            return null;
        }

        Node head = root;
        if (root.left != null) {
            Node leftList = transform(root.left);
            root.left = leftList.left;
            leftList.left.right = root;
            head = leftList;
        }

        Node tail = root;
        if (root.right != null) {
            Node rightList = transform(root.right);
            root.right = rightList;
            Node temp = rightList.left;
            rightList.left = root;
            tail = temp;
        }

        head.left = tail;
        return head;
    }

    @Value
    static class Result {
        Node head;
        Node tail;
    }

    @AllArgsConstructor
    public static class Node {
        int v;
        Node left;
        Node right;
    }
}
