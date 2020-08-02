package Week08;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/8/2
 */
public class Merge {
  public int[][] merge(int[][] intervals) {
    // 将输入按起点排序
    if (intervals.length == 0 || intervals[0].length == 0) return new int[0][0];
    int len = intervals.length;
    Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
    int[][] res = new int[len][2];
    // 其实位置遍历统计最小最大值范围
    int min = intervals[0][0], max = intervals[0][1], index = 0;
    for (int i = 1; i < len; i++) {
      if (intervals[i][0] > max) {
        res[index][0] = min;
        res[index++][1] = max;
        min = intervals[i][0];
        max = intervals[i][1];
      } else {
        min = Math.min(min, intervals[i][0]);
        max = Math.max(max, intervals[i][1]);
      }
    }
    // 最后一组
    res[index][0] = min;
    res[index][1] = max;
    return Arrays.copyOf(res, index + 1);
  }
}
