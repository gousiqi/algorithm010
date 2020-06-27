package Week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author gousiqi
 * @Description 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * @Date 2020/6/27
 */
public class PermuteUnique {
  List<List<Integer>> result = new ArrayList<>();
  public List<List<Integer>> permuteUnique(int[] nums) {
    //在全排列的基础上，需要判重剪枝，所以可以先对nums排序
    if (nums.length == 0) return result;
    Arrays.sort(nums);
    dfs(nums.length, nums, new ArrayList<>(), new int[nums.length]);
    return result;
  }

  /**
   * @param n 剩余的层数 到0即可输出
   * @param nums 条件数组
   * @param res 需维护的目标列表
   * @param tags 标记已使用的元素
   */
  private void dfs(int n, int[] nums, List<Integer> res, int[] tags) {
    if (n == 0) { //terminator
      result.add(new ArrayList<>(res));
      return;
    }

    //process & drill down 遍历nums添加可选的元素
    for (int i = 0; i < nums.length; i++) {
      if (tags[i] == 1) continue; //已使用的元素需要剪枝；
      if (i > 0 && tags[i-1] == 0 && nums[i] == nums[i-1]) continue;// 本次循环中已使用过的元素需要剪枝
      tags[i] = 1;
      res.add(nums[i]);
      dfs(n-1, nums, res, tags);
      tags[i] = 0; // backTrace
      res.remove(res.size()-1);
    }
  }
}
