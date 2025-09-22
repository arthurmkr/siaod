package ru.mab.siaod.leetcode;

/**
 * 2. Add Two Numbers
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers alg = new AddTwoNumbers();
        System.out.println(alg.addTwoNumbers(
                ListNode.create(9, 9, 9, 9, 9, 9, 9),
                ListNode.create(9, 9, 9, 9)));

        System.out.println(alg.addTwoNumbers(
                ListNode.create(5),
                ListNode.create(5)));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode();
        ListNode res = head;
        while (l1 != null || l2 != null || carry == 1) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int sum = v1 + v2 + carry;
            res.next =  new ListNode(sum % 10);
            res = res.next;
            carry = sum / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return head.next;
    }
}
