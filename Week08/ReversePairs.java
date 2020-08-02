package Week08;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/8/1
 */
public class ReversePairs {
  public int reversePairs(int[] nums) {
    if (nums.length < 2) return 0;
    // 分治+归并排序 等于左边的反转对pairs(0,mid)+右边的反转对pairs(mid+1, n - 1)+左右归并排序后的反转对 闭区间
    int n = nums.length;
    int[] temp = new int[n];
    return _reversePairs(nums, 0, n - 1, temp);
  }

  /**
   *
   * @param nums 需要计算反转对的数组
   * @param l 左边界
   * @param r 右边界
   * @return
   */
  private int _reversePairs(int[] nums, int l, int r, int[] temp) {
    if (l == r) return 0;
    int mid = l + (r - l >> 1);
    int leftPairs = _reversePairs(nums, l, mid, temp);
    int rightPairs = _reversePairs(nums, mid + 1, r, temp);
    int combinePairs = _combinePairs(nums, l, mid, r, temp);
    return leftPairs + rightPairs + combinePairs;
  }

  // 此时左右两边已经满足没有反转对 整体归并，将翻转对交换
  private int _combinePairs(int[] nums, int l, int mid, int r, int[] temp) {
    for (int k = l; k <= r; k++) {
      temp[k] = nums[k];
    }
    // 先遍历右边，左边存在两个指针，一个负责排序，一个负责统计大于两倍的位置,count统计翻转数
    int m = l, n = l, index = l, count = 0;
    for (int i = mid + 1; i <= r; i++) {
      while (m <= mid && temp[m] <= temp[i]) nums[index++] = temp[m++];
      while (n <= mid && temp[n] <= 2 * (long)temp[i]) n++; //找到大于两倍temp[i]的下标
      nums[index++] = temp[i];//其他情况，index位置的数要取temp[i]
      count += mid - n + 1;
    }
    while(m <= mid) nums[index++] = temp[m++]; //
    return count;

    // 同样可以先统计翻转对的数量，然后ArraysSort排序
  }
}
