package Week06;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/18
 */
public class MinPathSum {
  public int minPathSum(int[][] grid) {
    //定义dp[i][j]代表到达i,j的最小路径 ,最终需要求dp[m-1][n-1];
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m+1][n+1];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < m; i++) dp[i][0] = dp[i-1][0] + grid[i][0];
    for (int j = 1; j < n; j++) dp[0][j] = dp[0][j-1] + grid[0][j];
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
      }
    }
    return dp[m-1][n-1];
  }
}
