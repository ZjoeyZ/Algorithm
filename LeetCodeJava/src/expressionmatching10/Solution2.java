package expressionmatching10;

/*
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

        '.' 匹配任意单个字符
        '*' 匹配零个或多个前面的那一个元素
        所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

分解：
1,先不管* 这种无穷的情况
    则要求字符之间一一匹配
    分解：n个字符和n个字符的一一匹配分解成1个字符和1个字符的一一匹配
    第一个匹配完检查第二个：使用方法：1，遍历 2，递归
    之前用遍历没有写出来，所以这次选择递归
    递归：匹配字符串的第一个字符，先判断是否为空，然后给出匹配字符串第一个字符的结果，然后对子串调用同样的算法

    完善匹配第一个字符的方式 增加 . 字符，只要字符 和 . 匹配一定成功

    完成* 无穷字符匹配方式，需要判断下一字符是不是 *
    如果下一字符是*
    则 对 p的子串 和 s串调用同样的算法；

    问题 aaa 和 a*a false
    因为 * 直接把所有的a都匹配完了。
    那就把 * 保护 match 和可能不match 两种可能

    问题： ""
           "c*c*"
    冲突: 增加 s空，p不空的处理方式
*/
public class Solution2 {

    public boolean singleMatch(String match, String rule) {
        if (rule.endsWith(".")) {
            return true;
        }
        return match.endsWith(rule);
    }

    public boolean isMatch(String s, String p) {
        if (p.isEmpty() && !s.isEmpty()) {
            return false;
        } else if (p.isEmpty()) {
            return true;
        } else if (s.isEmpty()) {
            if (p.length() >= 2) {
                String next = p.substring(1, 2);
                if (next.equals("*")) {
                    return isMatch(s, p.substring(2));
                }
            }
            return false;
        } else {
            String match = s.substring(0, 1);
            String rule = p.substring(0, 1);

            if (p.length() >= 2) {
                String next = p.substring(1, 2);
                if (next.equals("*")) {
                    if (singleMatch(match, rule)) {
                        return isMatch(s, p.substring(2)) || isMatch(s.substring(1), p);
                    } else {
                        return isMatch(s, p.substring(2));
                    }
                }
            }
            if (singleMatch(match, rule)) {
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Solution2 so = new Solution2();
        System.out.println(so.isMatch("aaa", "a*"));
    }
}
