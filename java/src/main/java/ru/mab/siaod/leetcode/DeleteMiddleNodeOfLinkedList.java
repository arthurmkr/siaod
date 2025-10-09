package ru.mab.siaod.leetcode;

/**
 * 2095. Delete the Middle Node of a Linked List
 */
public class DeleteMiddleNodeOfLinkedList {
    public static void main(String[] args) {
        System.out.println(deleteMiddle(ListNode.of(1, 3, 4, 7, 1, 2, 6)));
        System.out.println(deleteMiddle(ListNode.of(1, 2, 3, 4)));
        System.out.println(deleteMiddle(ListNode.of(2, 1)));
    }

    public static ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode first = head;
        ListNode second = head;
        ListNode prevSecond = head;

        while (first != null && first.next != null) {
            prevSecond = second;
            second = second.next;
            first = first.next.next;
        }

        prevSecond.next = second.next;
        return head;
    }
}
