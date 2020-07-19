package Week06;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/19
 */
public class MaxCoins {
  // 方法一：回溯，遍历能扎的气球，维护最值，同时也记录每次扎过的气球
  // 超时 O(!n)
  int maxResult;
  public int maxCoins2(int[] nums) {
    _getCoins (nums.length, nums, 0);
    return maxResult;
  }
  /**
   * 穷举求和 (超时)
   * @param bal  记录还未扎的气球数
   * @param nums  当前的气球金额(-1达标已扎)
   * @param total  当前下探的和；
   */
  private void _getCoins(int bal, int[] nums, int total) {
    if (bal == 0) {
      maxResult = Math.max(maxResult, total);
      return;
    }
    int l, r, lval, rval; //定义左右气球的下标和值
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == -1) continue;
      //求左右气球的值
      l = i - 1;
      while (l >= 0 && nums[l] == -1) l--;
      lval = (l == -1) ? 1 : nums[l];
      r = i + 1;
      while (r < nums.length && nums[r] == -1) r++;
      rval = (r == nums.length) ? 1 : nums[r];

      int cur = nums[i];
      //drill down and reverse
      nums[i] = -1;
      _getCoins(bal - 1, nums, total + lval * cur * rval);
      nums[i] = cur;
    }
  }

  /**
   * 方法二：动态规划，将题目给边界1加入，定义dp[i][j]表示i和j之间的气球戳破后获得的金币最大值
   * bound:
   * 当j-i < 2 dp[i][j] = 0;
   * 当j-i = 2 dp[i][j] = coins[i] * coins[j] * coins[i+1]，可合并至others
   * others dp[i][j] = max(dp[i][k] + dp[k][i] + dp[i] * dp[k] * dp[j])
   * 求解dp[0][coins.length-1]
   */
  public int maxCoins(int[] nums) {
    int len = nums.length;
    if (len == 0) return 0;
    int[] coins = new int[len + 2];
    coins[0] = coins[len + 1] = 1;
    for (int i = 1; i <= len; i++) coins[i] = nums[i-1];

    int[][] dp = new int[len + 2][len +2];
    // 因为j-i >=2 时，才能开始获得金币数，所以i从 len+1-2开始倒序遍历
    for (int i = len - 1 ; i >= 0; i--) {
      for (int j = i + 2 ; j <= len + 1; j++) {
        for (int k = i + 1; k < j; k++) {
          dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + coins[i] * coins[k] * coins[j]);
        }
      }
    }
    return dp[0][len + 1];
  }
}
