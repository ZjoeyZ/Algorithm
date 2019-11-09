package expressionmatching10;

public class Solution4 {
    public boolean isMatch(String s, String p) {
        char[] sarr = s.toCharArray();
        char[] parr = p.toCharArray();
        int[][] result = new int[sarr.length + 1][parr.length + 1];
        return tryMatch(sarr, parr, 0, 0, result);
    }

    private boolean tryMatch(char[] sarr, char[] parr, int i, int j, int[][] result) {
        if (result[i][j] != 0) {
            return result[i][j] == 1;
        }
        if (i == sarr.length && j == parr.length) {
            result[i][j] = 1;
            return true;
        } else if (i == sarr.length) {
            // 处理，以*结尾的 a*
            if (j + 1 <= parr.length - 1) {
                if (parr[j + 1] == '*') {
                    boolean r = tryMatch(sarr, parr, i, j + 2, result);
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

        boolean firstMatch = sarr[i] == parr[j] || parr[j] == '.';
        if (j + 1 <= parr.length - 1 && parr[j + 1] == '*') {
            if (firstMatch) {
                // 处理a*a
                boolean r = tryMatch(sarr, parr, i, j + 2, result) || tryMatch(sarr, parr, i + 1, j, result);
                result[i][j] = r ? 1 : -1;
                return r;
            } else {
                boolean r = tryMatch(sarr, parr, i, j + 2, result);
                result[i][j] = r ? 1 : -1;
                return r;
            }
        }

        if (firstMatch) {
            boolean r = tryMatch(sarr, parr, i + 1, j + 1, result);
            result[i][j] = r ? 1 : -1;
            return r;
        }
        result[i][j] = -1;
        return false;
    }


    public static void main(String[] args) {
        Solution4 s3 = new Solution4();
        System.out.println(s3.isMatch("a", "ab*"));
    }
}
