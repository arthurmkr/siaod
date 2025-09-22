package ru.mab.siaod.leetcode;

/**
 * 21. Merge Two Sorted Lists
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode tmp;
        ListNode result;

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            tmp = result = list1;
            list1 = list1.next;
        } else {
            tmp = result = list2;
            list2 = list2.next;
        }

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tmp.next = list1;
                tmp = list1;
                list1 = list1.next;
            } else {
                tmp.next = list2;
                tmp = list2;
                list2 = list2.next;
            }
        }

        if (list1 != null) {
            tmp.next = list1;
        } else {
            tmp.next = list2;
        }

        return result;
    }
}
