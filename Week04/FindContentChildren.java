package Week04;

import java.util.Arrays;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/1
 */
public class FindContentChildren {
  public int findContentChildren(int[] g, int[] s) {
    int max = 0;
    if (g.length == 0 || s.length == 0) return max;
    Arrays.sort(g);
    Arrays.sort(s);
    int i = 0, j = 0;
    while (i < g.length && j < s.length) { //贪心 找尽可能刚好满足的
      if (s[j] >= g[i]) { // 最接近匹配的饼干进行分发
        i++;
        max++;
      }
      j++;
    }
    return max;
  }
}
