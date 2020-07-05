package Week04;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/1
 */
public class MaxProfit {
  public int maxProfit(int[] prices) { // 只要当天可以买卖，即可贪心
    int max = 0;
    if (prices.length == 0) return max;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        max += prices[i] - prices[i - 1];
      }
    }
    return max;
  }
}
