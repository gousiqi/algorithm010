package Week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

  /**
   * 有效的异位词分组
   * 整体思路：将某种数据集合进行分组的相关题目，优先考虑使用HashMap进行维护；HashMap<key, List>
   * 1. 遍历集合，根据题目条件得到key，去维护key对应的List（新建或者增加元素）
   * 2. 最后通过hashmap的values 得到 List<List>
   * 3. 本题条件是异位词，可以根据字符串字符数组排序后得到key；也可以对字符数组进行计数得到key
   *
   * 条件：输入的strs中没有null元素，完全相同的str也算有效异位词，str中只有小写字母
   */
  public List<List<String>> group(List<String> strs) {
    HashMap<String, List<String>> map = new HashMap<>();
    if (strs.size() == 0) return new ArrayList<>();
    for (String str : strs) {
      //方法一：通过字符数组排序得到key
      String key = _getKeyBySort(str);
      //方法二：通过统计字符数组各字符的出现次数进行排序
      //String key = _getKeyByCount(str);
      if (!map.containsKey(key)) map.put(key,new ArrayList<>());
      map.get(key).add(str);
    }
    return new ArrayList<>(map.values());
  }

  //通过统计字符数组各字符的出现次数进行排序
  private String _getKeyByCount(String str) {
    char[] chars = str.toCharArray();
    int[] counts = new int[26];
    for (char ch : chars) {
      counts[ch-'a'] ++;
    }
    StringBuffer buffer = new StringBuffer();
    for (int count : counts) {
      buffer.append("#");
      buffer.append(count);
    }
    return buffer.toString();
  }

  //通过字符数组排序得到key
  private String _getKeyBySort(String str) {
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    return String.valueOf(chars);
  }

  public static void main(String[] args) {
    List<String> strs = Arrays.asList("eat","tea","tan","ate","nat","bat");

    GroupAnagrams groupAnagrams = new GroupAnagrams();
    List<List<String>> result = groupAnagrams.group(strs);
    System.out.println(result);
  }
}