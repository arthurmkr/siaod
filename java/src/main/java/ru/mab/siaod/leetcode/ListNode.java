package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

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

    public static ListNode create(int... vals) {
        if(vals.length == 0) {
            return null;
        }
        ListNode head = new ListNode(vals[0]);
        ListNode cur = head;
        for (int i = 1; i < vals.length; i++) {
            cur.next = new ListNode(vals[i]);
            cur = cur.next;
        }

        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        do {
            sb.append('[')
                    .append(cur.val)
                    .append(']');

            cur = cur.next;
            if (cur != null) {
                sb.append("->");
            }
        } while (cur != null);
        return sb.toString();
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
