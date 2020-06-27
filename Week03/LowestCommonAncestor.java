package Week03;

/**
 * @Author gousiqi
 * @Description 最近公共祖先
 * @Date 2020/6/27
 */
public class LowestCommonAncestor {
  // 思路一：遇到的第一个p在其左子树且q在其右子树的节点，或者遇到的第一个p或者q，即为公共祖先
  // 方法: 递归
  TreeNode result = null;
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return result;
    sonHasNode(root, p, q);
    return result;
  }
  // 定义函数表示root的这棵树存在某一节点
  private boolean sonHasNode(TreeNode root, TreeNode p, TreeNode q) {
    //terminator
    if (root == null) return false;
    //drill down
    boolean left = sonHasNode(root.left, p, q);
    boolean right = sonHasNode(root.right, p, q);
    //proess
    if ((left && right) || (root.val == p.val || root.val == q.val) && (left || right)) {
      result = root;
    }
    return left || right || root.val == p.val || root.val == q.val;
  }

  // 思路二：设节点 root 为节点 p, q 的某公共祖先，
  //      若其左子节点 root.left 和右子节点 root.right 都不是 p,q的公共祖先，则称 root 是 “最近的公共祖先” 。
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    // terminator
    if (root == null || root == p || root == q) return root;
    // drill down：left,right 表示分别在左右子树找p或者q，
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    // 在哪一侧找到 即返回哪一侧
    if (left == null) return right;
    if (right == null) return left;
    return root;
  }
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}
