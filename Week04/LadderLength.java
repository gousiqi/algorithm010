package Week04;

import java.util.*;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/7/3
 */
public class LadderLength {
  //方法一：单向BFS
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    //1. 整理字典，将wordList放入hash表，方便判断某个单次是否在wordList中
    Set<String> dics = new HashSet<>(wordList);
    if (dics.size() == 0 || !dics.contains(endWord)) return 0;
    dics.remove(beginWord);//去掉可能存在的beginWord，避免多余的访问；

    //2. 图的广度优先遍历，必须使用一个队列表示未访问但能访问的节点（跟树的bfs一样），以及一个哈希表或者数组标记已访问的节点
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    Set<String> visit = new HashSet<>(); //不建议直接在dics中进行删除，普遍是新开一个表
    visit.add(beginWord);
    //3. beginWord尝试将每个字母替换为其他字母，若存在于wordList中，则存入待访问列表，准备下一次访问
    int step = 1, len = beginWord.length();
    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int k = 0; k < queueSize; k++){
        String word = queue.poll();
        for (int i = 0; i < len; i++) {
          char temp = word.charAt(i);
          for (char cur = 'a'; cur <= 'z' ; cur++) {
            if (cur == temp) continue; //原offer词跳过
            String newWord = word.substring(0,i) + cur + word.substring(i+1);
            if (dics.contains(newWord)) {
              if (endWord.equals(newWord)) return step + 1;
              if (!visit.contains(newWord)) {
                queue.offer(newWord);// 未访问过，则添加到待访问队列，然后标记已访问
                visit.add(newWord);
              }
            }
          }
        }
      }
      step++; //一层的可选目标循环结束，步数+1，再进入下一层循环
    }
    return 0;
  }

  //方法二：双向BFS
  public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
    //1. 整理字典，将wordList放入hash表，方便判断某个单次是否在wordList中
    Set<String> dics = new HashSet<>(wordList);
    if (dics.size() == 0 || !dics.contains(endWord)) return 0;
    dics.remove(beginWord);//去掉可能存在的beginWord，避免多余的访问；

    //2. 图的广度优先遍历，首先用一个哈希表或者数组标记已访问的节点
    Set<String> visit = new HashSet<>(); //不建议直接在dics中进行删除，普遍是新开一个表
    //3. 用两个visit分别表示起始节点和目标节点发起的bfs待访问节点集合；
    Set<String> beginVisit = new HashSet<>();
    beginVisit.add(beginWord);
    Set<String> endVisit = new HashSet<>();
    endVisit.add(endWord);
    int step = 1, len = beginWord.length();
    while (!beginVisit.isEmpty() && !endVisit.isEmpty()) {
      // 4. 选取集合数少的visit作为遍历目标，可以尽量减少访问
      if (beginVisit.size() > endVisit.size()) {
        Set<String> temp = beginVisit;
        beginVisit = endVisit;
        endVisit = temp;
      }
      Set<String> newBeginVisit = new HashSet<>();
      for (String word : beginVisit){
        for (int i = 0; i < len; i++) {
          char temp = word.charAt(i);
          for (char cur = 'a'; cur <= 'z' ; cur++) {
            if (cur == temp) continue; //原offer词跳过
            String newWord = word.substring(0,i) + cur + word.substring(i+1);
            if (dics.contains(newWord)) {
              if (endVisit.contains(newWord)) return step + 1;
              if (!visit.contains(newWord)) {
                newBeginVisit.add(newWord);// 未访问过，则添加到待访问队列，然后标记已访问
                visit.add(newWord);
              }
            }
          }
        }
      }
      beginVisit = newBeginVisit; // 代表已经扩散到下一层
      step++; //一层的可选目标循环结束，步数+1，再进入下一层循环
    }
    return 0;
  }
}
