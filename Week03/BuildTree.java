package Week03;

import java.util.Arrays;

/**
 * @Author gousiqi
 * @Description 根据一棵树的前序遍历与中序遍历构造二叉树
 * @Date 2020/6/27
 */
public class BuildTree {

  //思路：逆推思想：前：根+左子树+右子树 中：左子树+根+右子树
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    // 可以获取左右子树对应的前序和中序子数组，从而进行递归
    // terminator
    if (preorder.length == 0 || inorder.length == 0) return null;
    // process 得到根节点,及其左右节点，然后返回给上一层
    TreeNode root = new TreeNode(preorder[0]);
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == root.val) { // i的左边即为左子树，右边即为右子树，其长度与前序对应子树长度一致；
        //截取中序和前序子树
        int[] inorderLeft = Arrays.copyOfRange(inorder,0,i);
        int[] inorderRight = Arrays.copyOfRange(inorder,i+1, inorder.length);
        int[] preorderLeft = Arrays.copyOfRange(preorder,1,inorderLeft.length+1);
        int[] preorderRight = Arrays.copyOfRange(preorder,
            preorder.length - inorderRight.length, preorder.length);
        root.left = buildTree(preorderLeft,inorderLeft);
        root.right = buildTree(preorderRight,inorderRight);
        break;
      }
    }
    return root;
  }
}
