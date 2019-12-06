package linkedlist.removeNthNodeFromEndOfList;

import linkedlist.ListNode;

import java.util.ArrayList;

public class Solution {
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ArrayList<ListNode> arr = new ArrayList<>();
        for (ListNode i = head; i != null; i = i.next) {
            arr.add(i);
        }
        int delete = arr.size() - n;
        if (delete == 0) {
            return head.next;
        }

        ListNode p = arr.get(delete - 1);
        if (delete + 1 == arr.size()) {
            p.next = null;
            return head;
        }

        ListNode q = arr.get(delete + 1);
        p.next = q;
        return head;
    }

    // no dummy head two pointer
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null||head.next == null) {
            return null;
        }
        ListNode pre, curr;
        curr = head;
        pre = null;
        for (int i = 0; i < n; i ++) {
            curr = curr.next;
        }
        if (curr == null) {
            return head.next;
        }
        pre = head;
        while(curr !=null) {
            curr = curr.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }
    // dummy head two pointer;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy, pre, curr;
        dummy = new ListNode(0);
        dummy.next = head;
        pre = dummy;
        curr = dummy;

        for (int i = 0; i < n; i ++) {
            curr = curr.next;
        }
        while (curr.next != null) {
            curr = curr.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }
}
