package ru.mab.siaod.tree;

import static ru.mab.siaod.RandomUtil.nextInt;

public class TreeMain {

    public static void main(String[] args) {
        Tree tree = new Tree();

        for (int i = 0; i < 100; i++) {
            tree.insert(nextInt(100));
        }

        System.out.println(tree);
    }

    public static int maxDepth(Node node) {
        if (node == null) {
            return -1;
        }

        return Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight())) + 1;
    }
}
