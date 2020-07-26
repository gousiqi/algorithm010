package Week07;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/26
 */
public class Trie {
  TreeNode root;

  /** Initialize your data structure here. */
  public Trie() {
    root = new TreeNode();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    TreeNode node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.childs[word.charAt(i) - 'a'] == null) node.childs[word.charAt(i) - 'a'] = new TreeNode();
      node = node.childs[word.charAt(i) - 'a'];
      if (i == word.length() - 1) node.isEnd = true;
    }
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    int len = word.length();
    if (len == 0) return false;
    TreeNode node = root;
    for (int i = 0; i < len; i++) {
      if (node.childs[word.charAt(i) - 'a'] == null) return false;
      node = node.childs[word.charAt(i) - 'a'];
    }
    return node.isEnd;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    int len = prefix.length();
    if (len == 0) return false;
    TreeNode node = root;
    for (int i = 0; i < len; i++) {
      if (node.childs[prefix.charAt(i) - 'a'] == null) return false;
      node = node.childs[prefix.charAt(i) - 'a'];
    }
    return true;
  }

  class TreeNode {
    boolean isEnd;
    TreeNode[] childs;

    // 利用字母hash对应数组下标进行映射
    public TreeNode() {
      isEnd = false;
      childs = new TreeNode[26];
    }
  }
}
