package Week04;

/**
 * @Author gousiqi
 * @Description 搜索二维矩阵
 * @Date 2020/7/4
 */
public class SearchMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) return false;
    //二维数组映射到一维（对于某节点matrix[x][y],其在一维的坐标为 x * n + y）
    int m = matrix.length, n = matrix[0].length;
    int l = 0, r = m * n -1;
    while (l <= r) {
      int mid = l + (r - l >> 1);
      int x = mid / n, y = mid % n;
      if (matrix[x][y] == target) return true;
      else if (matrix[x][y] < target) l = mid + 1;
      else r = mid - 1;
    }
    return false;
  }
}
