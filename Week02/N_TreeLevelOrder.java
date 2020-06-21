package Week02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/6/21
 */
public class N_TreeLevelOrder {
  public List<List<Integer>> levelOrder(Node root) {
    //参考102题:二叉树的层序遍历 将左右节点的判断改成遍历children
    //bfs层序遍历，先处理的元素要先出，用队列进行维护
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int count = queue.size(); //queue是当前层的元素队列
      List<Integer> itr = new ArrayList<>();
      while (count > 0) {
        Node temp = queue.poll();
        itr.add(temp.val);
        if (!temp.children.isEmpty()) {
          for(Node node : temp.children) {
            if(node != null) queue.add(node);
          }
        }
        count--;
      }
      res.add(itr);
    }
    return res;
  }

  class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  };
}
