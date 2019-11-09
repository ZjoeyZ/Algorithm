package changeChar;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        HashSet<ArrayList<Integer>> result = getSubSet(nums); // 调用方法
        int evenset = 0;
        System.out.println("set");

        for (ArrayList<Integer> subSet : result) {
            System.out.println(subSet);
            if (subSet.size() < k) {
                continue;
            }
            int evenNum = 0;
            for (Integer num : subSet){
                if (num % 2 == 1) {
                    evenNum++;
                }
            }
            if (evenNum == k) {
                evenset++;
            }
        }
        return evenset;
    }

    public static HashSet<ArrayList<Integer>> getSubSet(int[] set) {
        HashSet<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>(); // 用来存放子集的集合，如{{},{1},{2},{1,2}}
        int length = set.length;
        int num = length == 0 ? 0 : 1 << (length); // 2的n次方，若集合set为空，num为0；若集合set有4个元素，那么num为16.

        // 从0到2^n-1（[00...00]到[11...11]）
        for (int i = 0; i < num; i++) {
            ArrayList<Integer> subSet = new ArrayList<Integer>();
            int index = i;
            for (int j = 0; j < length; j++) {
                if ((index & 1) == 1) { // 每次判断index最低位是否为1，为1则把集合set的第j个元素放到子集中
                    subSet.add(set[j]);
                }
                index >>= 1; // 右移一位
            }

            System.out.println(subSet);
            result.add(subSet); // 把子集存储起来
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s =  new Solution();
        int[] nums = {1,1,2,1,1};
        String a = "123".substring(1);
        System.out.println(s.numberOfSubarrays(nums, 3));
    }
}
