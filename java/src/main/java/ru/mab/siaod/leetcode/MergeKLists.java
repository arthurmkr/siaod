package ru.mab.siaod.leetcode;

import ru.mab.siaod.ArrayUtil;

import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public List<Integer> toList() {
        List<Integer> result = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) {
            result.add(cur.val);
            cur = cur.next;
        }

        return result;
    }
}


public class MergeKLists {
    static Random random = new Random();

    public static void main(String[] args) {
        ListNode[] lists = generateTestData();

        List<Integer> expected = prepareExpectedResult(lists);
//        System.out.println(expected);

        long time = System.currentTimeMillis();
        ListNode node = new Solution().mergeKLists(lists);
        System.out.println("size: " + expected.size() + ", time: " + (System.currentTimeMillis() - time));

        List<Integer> actual = node.toList();
//        System.out.println(actual);

        System.out.println(expected.equals(actual));
    }

    private static List<Integer> prepareExpectedResult(ListNode[] lists) {
        List<Integer> result = new ArrayList<>();
        for (ListNode listNode : lists) {
            if (listNode != null) {
                result.addAll(listNode.toList());
            }
        }

        Collections.sort(result);
        return result;
    }

    private static ListNode[] generateTestData() {
        int k = random.nextInt(10000);
        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            lists[i] = generateList();
        }

        return lists;
    }

    private static ListNode generateList() {
        int[] array = ArrayUtil.generateArray(random.nextInt(500), -10000, 10000);

        Arrays.sort(array);

        return convertToList(array);
    }

    private static void printList(ListNode node) {
        ListNode cur = node;
        while (cur != null) {
            System.out.print(cur.val);

            cur = cur.next;

            if (cur != null) {
                System.out.print(" -> ");
            }
        }
    }

    private static ListNode convertToList(int[] array) {
        if (array.length == 0) {
            return null;
        }

        ListNode prev = null;
        for (int i = array.length - 1; i >= 0; i--) {
            prev = new ListNode(array[i], prev);
        }

        return prev;
    }


}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int[] counts = new int[10000 * 2 + 1];

        for (ListNode list : lists) {
            ListNode cur = list;
            while (cur != null) {
                counts[cur.val + 10000]++;
                cur = cur.next;
            }
        }

        ListNode prev = null;
        for (int i = counts.length - 1; i >= 0; i--) {
            for (int j = 0; j < counts[i]; j++) {
                prev = new ListNode(i - 10000, prev);
            }
        }

        return prev;
    }
}