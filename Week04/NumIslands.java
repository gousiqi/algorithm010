package Week04;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/6/30
 */
public class NumIslands {

  //思路1 深度优先
  public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int length = grid.length, breadth = grid[0].length;
    int count = 0;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < breadth; j++) {
        if (grid[i][j] == '1') {
          _dfsMark(grid, i, j);
          count++;
        }
      }
    }
    return count;
  }
  private void _dfsMark (char[][] grid, int i, int j) {
    if (i == -1 || j == -1 || i == grid.length || j == grid[0].length || grid[i][j] == '0') return;//若 i ，j下标超出 或者改点不为陆地则终止；
    grid[i][j] = '0';
    _dfsMark(grid, i + 1, j);
    _dfsMark(grid, i - 1, j);
    _dfsMark(grid, i, j + 1);
    _dfsMark(grid, i, j - 1);
  }

  // 思路2 广度优先 维护一个队列 加入待处理的岛屿位置
  public int numIslands2(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int lr = grid.length, lc = grid[0].length;
    int count = 0;
    for (int i = 0; i < lr; i++) {
      for (int j = 0; j < lc; j++) {
        if (grid[i][j] == '1') {
          _bfs(grid, i, j);
          count++;
        }
      }
    }
    return count;
  }

  private void _bfs(char[][] grid, int i, int j) {
    Deque<int[]> dq = new LinkedList<>();
    dq.add(new int[]{i,j});
    while (!dq.isEmpty()) {
      int[] temp = dq.remove();
      int x = temp[0], y = temp[1];
      if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == '1') {
        grid[x][y] = '0';
        dq.add(new int[]{x - 1, y});
        dq.add(new int[]{x, y - 1});
        dq.add(new int[]{x + 1, y});
        dq.add(new int[]{x, y + 1});
      }
    }
  }

}
