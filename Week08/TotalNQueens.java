package Week08;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/8/1
 */
public class TotalNQueens {
  int res = 0;
  public int totalNQueens(int n) {
    if (n <= 0) return res;
    // 利用二进制位保存列，撇，捺的状态
    _dfsMark(n, 0, 0, 0, 0);
    return res;
  }

  private void _dfsMark(int n , int row, int col, int master, int slave) {
    //terminator
    if (row == n) {
      res++;
      return;
    }
    for (int i = 0; i < n; i++) {
      if (((col >> i) & 1) == 0 && ((master >> row + i) & 1) == 0 && ((slave >> row - i + n - 1) & 1) == 0) {
        col ^= (1 << i);
        master ^= (1 << row + i);
        slave ^= (1 << row - i + n - 1);
        _dfsMark(n, row + 1, col, master, slave);
        col ^= (1 << i);
        master ^= (1 << row + i);
        slave ^= (1 << row - i + n - 1);
      }
    }
  }
}
