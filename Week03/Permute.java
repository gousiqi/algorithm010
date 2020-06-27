package Week03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/6/27
 */
public class Permute {

  List<List<Integer>> result = new ArrayList<>();

  //回溯思想：每次选择一个元素，tags标记已添加过的元素，进入下一次选择，一共选择nums.length次
  public List<List<Integer>> permute(int[] nums) {
    if (nums.length == 0) return result;
    dfs(nums.length, nums, new ArrayList<>(), new int[nums.length]);
    return result;
  }

  private void dfs(int capacity, int[] nums, List<Integer> res , int[] tags) {
    //terminator
    if (capacity == 0) {
      result.add(new ArrayList<>(res));
      return;
    }
    //process & drill down
    for (int i = 0; i < nums.length; i++) {
      if (tags[i] == 1) continue; //判断是否已添加过
      tags[i] = 1;
      res.add(nums[i]);
      dfs(capacity-1, nums, res, tags);
      //backtrace
      tags[i] = 0;
      res.remove(res.size()-1);
    }
  }
}
