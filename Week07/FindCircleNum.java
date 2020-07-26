package Week07;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/26
 */
public class FindCircleNum {
  Union union;
  public int findCircleNum1(int[][] M) {
    union = new Union(M.length);
    for (int i = 0; i < M.length; i++) {
      for (int j = i + 1; j < M.length; j++) {
        if (M[i][j] == 1) {
          union.union(i, j);
        }
      }
    }
    return union.count();
  }
  class Union {
    private int count;
    private int[] parent;

    public Union(int n){
      this.count = n;
      parent = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    public void union(int x, int y) {
      int rootx = find(x);
      int rooty = find(y);
      if (rootx == rooty) return ;
      parent[rootx] = rooty;
      count--;
    }

    public int find(int x) {
      if (parent[x] == x) return x;
      while (x != parent[x]) {
        parent[x] = parent[parent[x]]; // 此处压缩路径
        x = parent[x];
      }
      return x;
    }
    public int count() { return count; }
  }

  // 方法二：dfs 当a和b是朋友时，b的所有朋友都会认定为a的朋友，以此递归
  public int findCircleNum(int[][] M) {
    int n = M.length, count = 0;
    if (n == 0) return 0;
    int[] visited = new int[n];
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        _dfsMark(i, visited, M);
        count++;// 至少是自己的朋友
      }
    }
    return count;
  }
  // 标记第i个学生的所有朋友关系
  private void _dfsMark(int i, int[] visited, int[][] M) {
    for (int j = 0; j < visited.length; j++) {
      if (visited[j] == 0 && M[i][j] ==1) {
        visited[j] = 1;
        _dfsMark(j, visited, M);
      }
    }
  }
}
