package Week09;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/8/8
 */
public class LongestValidParentheses {

  // 第二遍
  public int longestValidParentheses(String s) {
        // 栈的思路，左括号入栈，右括号出栈，统计中间连续的长度
        int len = s.length();
        if (len == 0) return len;
        int maxLenth = 0;
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        stack.offer(-1); // 定义哨兵边界
        for (int i = 0; i < len; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                int left = stack.pollFirst();
                if (stack.isEmpty()) stack.offer(i); // 左括号已经被取完了 新的哨兵边界
                maxLenth = Math.max(maxLenth,i - stack.peek());
            }
        }
        return maxLenth;
    }

  // 动态规划 dp[i]表示是s.char[i]结尾的子串最长有效括号的长度
  public int longestValidParentheses2(String s) {
    int len = s.length();
    int[] dp = new int[len];
    int maxLength = 0;
    for (int i = 1; i < len; i++) {
      if (s.charAt(i) == ')') {
        if (s.charAt(i - 1) == '(') dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
          // 前一个也是右括号,则判断前一个有效括号之前的字符
        else if(i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '(') {
          // 要加上可能的前面一位dp[i - dp[i - 1] - 2]的有效长度
          dp[i] = dp[i - 1] + (i - dp[i - 1] > 1 ? dp[i - dp[i - 1] - 2] : 0) + 2;
        }
      }
      maxLength = Math.max(dp[i], maxLength);
    }
    return maxLength;
  }
}
