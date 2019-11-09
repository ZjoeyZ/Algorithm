package question1towsum;

/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
并返回他们的数组下标。

        你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

        示例:

        给定 nums = [2, 7, 11, 15], target = 9

        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 1, 找到数组中任意某数x和 target所差的 值
    // 2, 遍历数组中除了x， 找到这个值
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            for (int j = i; j < nums.length; j++) {
                if (need == nums[j]) {
                    int[] a = new int[2];
                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
        }
        return null;
    }

    // 使用HashMap
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 忽略了出现重复key的情况，值
            map.put(nums[i], i);
            System.out.println("index" + i + " " + nums[i]);
        }
        System.out.println(map);
        for (int j = 0; j < nums.length; j++) {
            int need = target - nums[j];

            if (map.containsKey(need) && map.get(need) != j) {
                int needIndex = map.get(need);
                System.out.println("index" + j + " get need " + needIndex);
                if (j < needIndex) {
                    return new int[]{j, needIndex};
                }
            }
        }
        return null;
    }
}