package Week03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author gousiqi
 * @Description 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
 * @Date 2020/6/27
 */
public class Combination {    // 回溯法：二叉树思想，从n到1，每个数作为一层，选或者不选，维护k的剩余量

  List<List<Integer>> result = new ArrayList<>();

  public List<List<Integer>> combine(int n, int k) {
    if (n < k) return result;
    _combineTarget(n,k,new ArrayList<>());
    return result;
  }
  private void _combineTarget(int n, int k, List<Integer> target) {
    //terminator k=0表示target满足条件 n始终不小于k 此处无需校验
    if (k == 0) {
      result.add(target);
      return;
    }
    //process & drill down：可以选择后下探，或者不选直接下探
    if (n > k) _combineTarget(n-1,k,target); // 不选该元素的情况 n>k即剪枝，若n不大于k 只能必选；
    List<Integer> newTarget = new ArrayList<>(target); // 选该元素的情况，此时需新建一个list
    newTarget.add(n);
    _combineTarget(n-1,k-1,newTarget);
  }
}
