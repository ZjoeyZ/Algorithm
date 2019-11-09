package question653twosum4;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        boolean result = false;
        List<Integer> list = new ArrayList<>();
        preOreder(root, list);

        int low = 0;
        int high = list.size() - 1;
        while (low < high) {
            int sum = list.get(low) + list.get(high);
            if (sum == k) {
                result = true;
                break;
            } else if (sum < k) {
                low++;
            } else {
                high--;
            }
        }
        return result;
    }

    private void preOreder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        preOreder(root.left, list);
        list.add(root.val);
        preOreder(root.right, list);
    }
}