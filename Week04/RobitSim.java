package Week04;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/2
 */
public class RobitSim {
  public int robotSim(int[] commands, int[][] obstacles) {
    //整理障碍点，将所有障碍点用set存储起来，方便判断 △ 多次判断某元素在不在一个给定的多维数组，用set更高效
    Set<String> set = new HashSet<>();
    if (obstacles.length != 0) {
      for (int i = 0; i < obstacles.length; i++) {
        String value = obstacles[i][0] + "#" +obstacles[i][1];
        set.add(value);
      }
    }
    int x = 0, y = 0, max = 0, direction = 10000; //%4等于0时，y++; 1时x++; 2时y--;3时x--；可用二维数组或两个数组表示
    int[] di = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    for (int command : commands) {
      if (command == -1) direction++;
      else if (command == -2) direction--;
      else {
        int index = direction % 4; //只要没遇到障碍物，就可以往前继续走
        while ( command-- > 0 && !set.contains((x+di[index]) + "#" + (y + dy[index])) ) {
          x += di[index];
          y += dy[index];
        }
      }
      max = Math.max(max, x * x + y * y);
    }
    return max;
  }
}
