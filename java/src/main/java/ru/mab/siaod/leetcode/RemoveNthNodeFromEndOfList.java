package ru.mab.siaod.leetcode;

/**
 * 19. Remove Nth Node From End Of List
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList alg = new RemoveNthNodeFromEndOfList();

        System.out.println(alg.removeNthFromEnd(ListNode.create(1, 2, 3, 4, 5), 2));
        System.out.println(alg.removeNthFromEnd(ListNode.create(1, 2, 3, 4, 5), 5));
        System.out.println(alg.removeNthFromEnd(ListNode.create(1), 1));
        System.out.println(alg.removeNthFromEnd(ListNode.create(1, 2), 1));
    }

//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode cur = head;
//        ListNode forRemove = head;
//
//        int i;
//        for (i = 0; i < n && cur != null; i++) {
//            cur = cur.next;
//        }
//
//        if (cur == null) {
//            return i == n ? head.next : cur;
//        }
//
//        while (cur.next != null) {
//            cur = cur.next;
//            forRemove = forRemove.next;
//        }
//
//        forRemove.next = forRemove.next.next;
//
//        return head;
//    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode[] nodes = new ListNode[30];

        int i = 0;
        while (head != null) {
            nodes[i++] = head;
            head = head.next;
        }

        if (i == n) {
            return nodes[1];
        } else {
            nodes[i - n - 1].next = nodes[i - n].next;
            return nodes[0];
        }
    }
}
