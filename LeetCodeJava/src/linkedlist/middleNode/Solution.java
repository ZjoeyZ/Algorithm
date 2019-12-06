package linkedlist.middleNode;

import linkedlist.ListNode;

class Solution {
    public ListNode middleNode1(ListNode head) {
        int len = 0;
        for (ListNode i = head; i != null; i = i.next) {
            len += 1;
        }
        int middle = len / 2 + 1;

        for (int i = 1; i < middle; i++) {
            head = head.next;
        }
        return head;
    }

    // two pointer
    public ListNode middleNode(ListNode head) {
        ListNode mid = head;
        ListNode last = head;
        while (last != null && last.next != null) {
            mid = mid.next;
            last = last.next.next;
        }
        return mid;
    }
}