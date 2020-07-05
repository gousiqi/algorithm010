package Week04;

/**
 * @Author gousiqi
 * @Description 搜索旋转排序数组
 * @Date 2020/7/4
 */
public class SearchHalfOrderArray {
  public int search(int[] nums, int target) {
    //思路1 利用二分查找
    if (nums.length == 0) return -1;
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left >> 1);
      if (nums[mid] == target) return mid; // 终结
      if (nums[left] <= nums[mid]) { //左边有序
        if (nums[left] <= target && target < nums[mid]) {
          right = mid - 1;
        } else left = mid + 1; // 无需考虑m+1是不是旋转点
      }else { //右边有序
        if (nums[mid] < target && target <= nums[right]) {
          left = mid + 1;
        } else right = mid - 1;
      }
    }
    return -1;
  }
}
