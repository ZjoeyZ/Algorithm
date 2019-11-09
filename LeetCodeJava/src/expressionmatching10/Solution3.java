package expressionmatching10;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 问题：
 *  1，以*结尾的 a*
 *  2，*两边是重复字符 a*a
 */
public class Solution3 {
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        } else if (s.length() == 0) {
            // 处理，以*结尾的 a*
            char p2 = 0;
            if (p.length() >= 2) {
                p2 = p.charAt(1);
            }
            if (p2 == '*') {
                return isMatch(s, p.substring(2));
            }
            return false;
        } else if (p.length() == 0) {
            return false;
        }
        char s1 = s.charAt(0);
        char p1 = p.charAt(0);
        char p2 = 0;
        if (p.length() >= 2) {
            p2 = p.charAt(1);
        }
        if (p2 == '*') {
            if (p1 == s1 || p1 == '.') {
                // 处理a*a
                return isMatch(s, p.substring(2)) || isMatch(s.substring(1), p);
            } else {
                return isMatch(s, p.substring(2));
            }
        }
        if (p1 == s1 || p1 == '.') {
            return isMatch(s.substring(1), p.substring(1));
        }
        return false;
    }

    public static void main(String[] args) {
        Solution3 s3 = new Solution3();
        System.out.println(s3.isMatch("aaa", "a*a"));
    }
}
