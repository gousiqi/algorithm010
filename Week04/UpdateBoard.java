package Week04;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/3
 */
public class UpdateBoard {
  private final int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1}; //周围节点的x坐标差
  private final int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1}; //周围节点的y坐标差

  public char[][] updateBoard(char[][] board, int[] click) {
    //思路：DFS
    int x = click[0], y = click[1];
    if (board[x][y] == 'M') board[x][y] = 'X'; //踩到雷
    else if (board[x][y] == 'E') _dfs(board, x, y); //未踩雷
    return board;
  }

  private void _dfs(char[][] board, int x, int y) {
    int count = _getCount(board, x, y); //查询雷的数量
    if (count == 0) { //该节点周围无雷 可继续扩张
      board[x][y] = 'B';
      for (int i = 0; i < dx.length; i++) {
        int m = x + dx[i];
        int n = y + dy[i];
        if (m >= 0 && m < board.length && n>=0 && n < board[0].length && board[m][n] == 'E') {
          _dfs(board, m, n); //将周围的所有E节点(未挖出的空方块)挖出
        }
      }
    }
    else board[x][y] = Character.forDigit(count, 10); //该节点周围有雷，替换为雷的数量即可
  }

  //统计节点周围的雷数量
  private int _getCount(char[][] board, int x, int y) {
    int count = 0;
    for (int i = 0; i < dx.length; i++) {
      int m = x + dx[i];
      int n = y + dy[i];
      if (m >= 0 && m < board.length && n>=0 && n < board[0].length) {
        if (board[m][n] == 'M' || board[m][n] == 'X') count++;
      }
    }
    return count;
  }
}
