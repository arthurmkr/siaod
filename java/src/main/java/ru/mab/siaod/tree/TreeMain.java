package ru.mab.siaod.tree;

import java.util.ArrayList;
import java.util.List;

import static ru.mab.siaod.RandomUtil.nextInt;

public class TreeMain {

    public static void main(String[] args) {
        Tree tree = new Tree();

        int vs[] = {38, 59, 47, 90, 97};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
//            int v = nextInt(100);
            int v = vs[i];
            list.add(v);
            tree.insert(v);
        }


        System.out.println(tree);

        for(int v : list) {
            tree.delete(v);
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
