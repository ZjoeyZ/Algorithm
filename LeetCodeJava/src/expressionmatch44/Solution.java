package expressionmatch44;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 */

public class Solution {
    public boolean isMatch(String s, String p) {
        char[] sarr = s.toCharArray();
        char[] parr = p.toCharArray();
        int[][] result = new int[sarr.length + 1][parr.length + 1];
        return match(sarr, parr, 0, 0, result);
    }

    private boolean match(char[] sarr, char[] parr, int i, int j, int[][] result) {
        if (result[i][j] != 0) {
            return result[i][j] == 1;
        }
        if (i == sarr.length && j == parr.length) {
            result[i][j] = 1;
            return true;
        } else if (i == sarr.length) {
            if (j < parr.length) {
                if (parr[j] == '*') {
                    boolean r = match(sarr, parr, i, j + 1, result);
                    result[i][j] = r ? 1 : -1;
                    return r;
                }
            }
            result[i][j] = -1;
            return false;
        } else if (j == parr.length) {
            result[i][j] = -1;
            return false;
        }
        if (sarr[i] == parr[j] || parr[j] == '?') {

            boolean r = match(sarr, parr, i + 1, j + 1, result);
            result[i][j] = r ? 1 : -1;
            return r;
        }
        if (parr[j] == '*') {
            boolean r = match(sarr, parr, i + 1, j, result) || match(sarr, parr, i, j + 1, result);
            result[i][j] = r ? 1 : -1;
            return r;
        }
        result[i][j] = -1;
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isMatch("adceb",
                "*a*b"));
    }
}
