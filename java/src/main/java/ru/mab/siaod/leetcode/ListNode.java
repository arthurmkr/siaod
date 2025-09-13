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
