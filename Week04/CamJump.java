package Week04;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/4
 */
public class CamJump {
  public boolean canJump(int[] nums) {
    if (nums.length == 1) return true;
    //思路1：巧妙理解：寻找每个元素0，判断前面的元素能否跳过它即可：
    boolean result = true; //不存在元素0必true
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == 0) {
        result = false;
        for (int k = 0; k < i; k++) {
          if (nums[k] > (i - k)) result = true;
        }
        if (!result) break; //一旦某0节点无法跳过，即报错
      }
    }
    return result;
  }

  public boolean canJump2(int[] nums) {
    if (nums.length == 1) return true;
    //思路2：贪心算法，每个节点只要能被最大距离访问到，就继续访问该节点能到的最远距离；一旦某节点无法访问到，即false；
    int maxLength = 0;
    for (int i = 0; i < nums.length; i++) {
      if (maxLength < i) return false;
      maxLength = Math.max(maxLength, nums[i] + i);
    }
    return true;
  }
}
