package Week02;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/6/21
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetCode 94、144、145题合并
 * 二叉树的前中后序遍历：迭代法
 * 维护栈的方式进行遍历
 */
public class BinaryTreeTraversal {

  public List<Integer> traversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    Stack<TreeNode> stack = new Stack<>();
    //先插入root
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode temp = stack.pop();
      if (temp != null) { //不为空表示该节点未被处理过，作为根节点进行压栈处理；
        //前序 根左右 压栈顺序为右左根
        if (temp.right != null) stack.push(temp.right);
        if (temp.left != null) stack.push(temp.left);
        stack.push(temp);
        stack.push(null);
        //中序 左根右 压栈顺序为右根左
        if (temp.right != null) stack.push(temp.right);
        stack.push(temp);
        stack.push(null);
        if (temp.left != null) stack.push(temp.left);
        //后序 左右根 压栈顺序为根右左
        stack.push(temp);
        stack.push(null);
        if (temp.right != null) stack.push(temp.right);
        if (temp.left != null) stack.push(temp.left);
      } else {
        res.add(stack.pop().val); // 取出的栈顶为空表示遇到了处理标记，新的栈顶元素为之前处理过的根节点，直接写入res即可；
      }
    }
    return res;
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
      this.val = val;
    }
  }
}
