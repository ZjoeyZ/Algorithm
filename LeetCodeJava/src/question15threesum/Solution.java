package question15threesum;

import java.util.*;

/*
15, 三数之和
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
        使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
        例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

        满足要求的三元组集合为：
        [
        [-1, 0, 1],
        [-1, -1, 2]
        ]
问题？若果两个元素值相同，位置不同，算不算不重复？重复了
    那如何消除answers 中的[[-1,0,1]，[0,1,-1]]只取一个？
    让他们内部有序，然后使用 集合 自动去重
分解：找出所有满足条件的三元组 -> 找出一个满足条件的三元组
    n个a -> 1个a
    n个b -> 1个b， 遍历剩余的数组，看看有没有一个能使他们的和为0
    遍历b右边的值

优化方法：
    使用hash map，减少循环次数
    使用 排序 + 判断全零数组，直接输出
*/

public class Solution {
    public List<List<Integer>> threeSum1(int[] nums) {
        HashSet<List<Integer>> answers = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int b = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (a + b + nums[k] == 0) {
                        if (a <= b && b <= nums[k]) {
                            answers.add(new ArrayList<>(Arrays.asList(a, b, nums[k])));
                        } else if (a <= nums[k] && nums[k] <= b) {
                            answers.add(new ArrayList<>(Arrays.asList(a, nums[k], b)));
                        } else if (b <= nums[k] && nums[k] <= a) {
                            answers.add(new ArrayList<>(Arrays.asList(b, nums[k], a)));
                        } else if (b <= a && a <= nums[k]) {
                            answers.add(new ArrayList<>(Arrays.asList(b, a, nums[k])));
                        } else if (nums[k] <= a && a <= b) {
                            answers.add(new ArrayList<>(Arrays.asList(nums[k], a, b)));
                        } else if (nums[k] <= b && b <= a) {
                            answers.add(new ArrayList<>(Arrays.asList(nums[k], b, a)));
                        }
                    }
                }
            }
        }

        return new ArrayList<>(answers);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> answers = new HashSet<>();
        Arrays.sort(nums);
        if (nums.length == 0) {
            return new ArrayList<>(answers);
        }
        if (nums.length >= 3 && nums[0] == 0 && nums[nums.length - 1] == 0) {
            ArrayList<Integer> zero = new ArrayList<>();
            zero.add(0);
            zero.add(0);
            zero.add(0);
            answers.add(zero);
            return new ArrayList<>(answers);
        }
        Map<Integer, Integer> complements = new HashMap<>();
        for (int n = 0; n < nums.length; n++) {
            complements.put(nums[n], n);
        }
        for (int i = 0; i < nums.length - 2; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int b = nums[j];
                int complement = -a - b;
                if (!complements.containsKey(complement)) {
                    continue;
                }
                int k = complements.get(complement);
                if (k == i || k == j) {
                    continue;
                }
                if (a + b + complement == 0) {
                    if (a <= b && b <= complement) {
                        answers.add(new ArrayList<>(Arrays.asList(a, b, complement)));
                    } else if (a <= complement && complement <= b) {
                        answers.add(new ArrayList<>(Arrays.asList(a, complement, b)));
                    } else if (b <= complement && complement <= a) {
                        answers.add(new ArrayList<>(Arrays.asList(b, complement, a)));
                    } else if (b <= a && a <= complement) {
                        answers.add(new ArrayList<>(Arrays.asList(b, a, complement)));
                    } else if (a <= b) {
                        answers.add(new ArrayList<>(Arrays.asList(complement, a, b)));
                    } else {
                        answers.add(new ArrayList<>(Arrays.asList(complement, b, a)));
                    }
                }
            }
        }

        return new ArrayList<>(answers);
    }
}
