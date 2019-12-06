package linkedlist.mergeKSortedList;

import linkedlist.ListNode;

import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        int len = lists.length;
        if (len == 1) {
            return lists[0];
        }

        ListNode head = new ListNode(0), work = head;
        int min = 0, nullNum = 0, index = -1;
        int beforeMin = 0;

        // 确定初始下标
        for (ListNode l: lists) {
            index += 1;
            if (l != null) {
                min = index;
            }
        }

        // 找到最小结点的下标
        while (true) {
            nullNum = 0;
            index = -1;
            for (ListNode l : lists) {
                index += 1;
                if (l == null) {
                    nullNum++;
                    continue;
                }
                if (lists[min].val > l.val) {
                    beforeMin = min;
                    min = index;
                }
            }
            if (nullNum == len) {
                break;
            }
            // System.out.println("最小结点 index:" + min + " value:" + lists[min].val);

            // 把最小结点加入 head 链表中

            work.next = lists[min];
            work = work.next;
            lists[min] = lists[min].next;

            min = beforeMin;

        }
        return head.next;
    }

    // 利用数组，搜集最小值，生成ListNode

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        int len = lists.length;
        if (len == 1) {
            return lists[0];
        }

        ArrayList<Integer> n = new ArrayList<>();

        int min = 0, nullNum = 0, frontMin = 0;
        // 确定初始下标
        for (ListNode l: lists) {
            if (l != null) {
                min = l.val;
                break;
            }
        }

        // 找到最小结点的下标
        while (true) {
            nullNum = 0;
            for (int i = 0; i < len; i++) {
                if (lists[i] == null) {
                    nullNum++;
                    continue;
                }
                if (min > lists[i].val) {
                    frontMin = min;
                    min = lists[i].val;
                    lists[i] = lists[i].next;
                }
            }
            if (nullNum == len) {
                break;
            }
             System.out.println("最小结点 " + min);

            // 把最小结点加入 head 链表中
            n.add(min);
            min = frontMin;
        }

        // int list to nodeList
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : n) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

}
