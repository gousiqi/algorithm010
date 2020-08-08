package Week09;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/8/8
 */
public class LengthOfLIS {
  // 第二遍 动态规划定定义dp[i]为最长上升子序列末位的长度
  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    if (n < 2) return n;
    int[] dp = new int[n];
    dp[0] = 1;
    int res = 0;
    for (int i = 1; i < n; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], 1 + dp[j]);
      }
      res = Math.max(res, dp[i]);
    }
    return res;
  }
}
