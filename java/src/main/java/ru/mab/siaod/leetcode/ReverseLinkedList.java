package ru.mab.siaod.leetcode;

/**
 * 206. Reverse Linked List
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        System.out.println(reverseList(ListNode.create(1, 2, 3, 4, 5)));
        System.out.println(reverseList(ListNode.create(1, 2)));
        System.out.println(reverseList(ListNode.create()));
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode cur = head;

        while (cur.next != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        cur.next = prev;
        return cur;
    }
}
