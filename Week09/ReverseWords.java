package Week09;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/8/8
 */
public class ReverseWords {

  public String reverseWords(String s) {
    String[] words = s.trim().split(" +");
    Collections.reverse(Arrays.asList(words));
    return String.join(" ", words);
  }
}
