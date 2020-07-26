package Week07;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/26
 */
public class NumIslands {
  // 思路3 并查集
  Unions unions;
  public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int m = grid.length, n = grid[0].length;
    unions = new Unions(m * n + 1); // 最后一个作为虚拟节点，将海域统一链接
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int index = i * n + j;
        if (grid[i][j] == '1') { // 将右边和下面的节点进行判断，陆地进行联通
          // 右下和本身三个节点对应的下标
          int down = (i + 1) * n + j, right = i * n + j + 1;
          if (i + 1 < m && grid[i + 1][j] == '1') unions.union(down, index);
          if (j + 1 < n && grid[i][j + 1] == '1') unions.union(right, index);
        } else unions.union(index, m * n); // 海域连到末尾虚拟节点
      }
    }
    return unions.count()-1; //去掉海域
  }
  class Unions {
    private int count;
    private int[] parent;
    public Unions(int n) {
      count = n;
      parent = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }
    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX == rootY) return;
      parent[rootX] = rootY;
      count--;
    }
    public int find(int x) {
      while (x != parent[x]) {
        parent[x] = parent[parent[x]]; //路径压缩
        x = parent[x];
      }
      return x;
    }
    public int count() { return count;}
  }

  // 第二遍dfs 从陆地位置开始下探，访问到的陆地都标记为海域
  int[][] direction = new int[][]{{-1, 0},{1, 0},{0, 1},{0, -1}};
  public int numIslands1 (char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    char[][] visited = grid;
    int m = visited.length, n = visited[0].length, count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[i][j] == '1') {
          _dfsMark(visited, i, j, m, n);
          count++;
        }
      }
    }
    return count;
  }

  private void _dfsMark(char[][] visited, int i, int j, int m, int n) {
    //terminator
    if (i == m || j == n || i < 0 || j < 0 || visited[i][j] == '0') return;
    visited[i][j] = '0';
    for (int[] dir : direction) {
      _dfsMark(visited, i + dir[0], j + dir[1], m, n);
    }

  }
}
