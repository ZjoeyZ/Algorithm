package expressionmatching10;

/*
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

        '.' 匹配任意单个字符
        '*' 匹配零个或多个前面的那一个元素
        所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

分解：把p按照*分成n份rules，rules分为无星字符约束和多星字符约束
给s加一个指针i，表示检查到那个在字符了
对于每份rules：让单字符约束和i比较
                成功或者是'.' 字符 i+1
                失败退出
               让多字符约束和i比较
               如果是‘.*’ 且没有next元素，直接成功退出。
               如果是‘.*’ 有next元素，
                最后rule不是‘.*’字符串,逆转，rules逆转，再次调用本方法
                最后rule也是‘.*’字符串，
               成功i+1
                失败进入下一个rule
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Integer> getIndex(String strings, String str) {
        List<Integer> list = new ArrayList<>();
        int flag = 0;
        while (strings.contains(str)) {
            //截取包含自身在内的前边部分
            String aa = strings.substring(0, strings.indexOf(str) + str.length());
            flag = flag + aa.length();
            list.add(flag - str.length());
            strings = strings.substring(strings.indexOf(str) + str.length());
        }
        return list;
    }

    public static String[] getRules(String p) {
        List<Integer> list = getIndex(p, "*");
        if (list == null) throw new AssertionError();
        StringBuilder b = new StringBuilder();

        int preIndex = 0;
        for (Integer i : list) {
            b.append(p, preIndex, i - 1);
            b.append(" ");
            b.append(p, i - 1, i + 1);
            b.append(" ");
            preIndex = i + 1;
        }
        // 加入后一部分元素
        b.append(p, preIndex, p.length());
        p = b.toString();
        p = p.trim();
        return p.split("\\s+");
    }

    public boolean isMatch(String s, String p) {
        boolean result = true;
        String[] rules = getRules(p);
        int i = 0;

        for (String rule : rules) {
            if (!rule.endsWith("*")) {
                //让单字符约束和i比较
                int len = rule.length();
                String ch = s.substring(i, i + len);
                if (!ch.equals(rule)) {
                    result = false;
                    break;
                } else {
                    i = i + len;
                }
            } else {
                //让多字符约束和i比较
                String noConstrain = rule.substring(0, rule.length() - 1);
                while (s.substring(i, i + 1).equals(noConstrain)) {
                    i++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "mississippi";
        String p = "is*is*p*";
        Solution solution = new Solution();
        boolean r = solution.isMatch(s, p);
        System.out.println(r);
    }
}
