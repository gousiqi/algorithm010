package Week04;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/4
 */
public class FindMin {
  public int findMin(int[] nums) {
    if (nums.length == 0) return -1;
    int n = nums.length;
    //二分查找
    int l = 0, r = n - 1;
    while (l < r) {
      int mid = l + (r - l >> 1);
      if (nums[mid] > nums[r]) l = mid + 1;
      else r = mid;
    }
    return nums[l];
  }
}
