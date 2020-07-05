package Week04;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/1
 */
public class LemonadeChange {
  public boolean lemonadeChange(int[] bills) {
    // 贪心算法 遍历一遍，遇到20时，优先使用10元去扣除
    int five = 0, ten = 0, twenty = 0;
    for (int num : bills) {
      if (num == 5) five++;
      else if (num == 10) {
        ten++;
        five--;
      }else {
        twenty++;
        if (ten > 0) ten--;
        else five = five - 2;
        five--;
      }
      if (five < 0) return false;
    }
    return true;
  }
}
