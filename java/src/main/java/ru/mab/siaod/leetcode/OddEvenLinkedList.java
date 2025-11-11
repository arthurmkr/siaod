package ru.mab.siaod.leetcode;

/**
 * 328. Odd Even Linked List
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        System.out.println(oddEvenList(ListNode.of(1, 2, 3, 4, 5)));
        System.out.println(oddEvenList(ListNode.of(2, 1, 3, 5, 6, 4, 7)));
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode evenHead = head.next;
        ListNode even = evenHead;
        ListNode odd = head;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }
}
