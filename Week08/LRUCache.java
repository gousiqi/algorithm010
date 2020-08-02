package Week08;

import java.util.HashMap;

/**
 * @Author gousiqi
 * @Description
 * @Date 2020/8/1
 */
public class LRUCache {
  private HashMap<Integer, Node> map;
  private final int capacity;
  private Node head;
  private Node tail;
  public LRUCache(int capacity) {
    this.capacity = capacity;
    // 初始化大小避免了扩容，可以不定义大小
    map = new HashMap<>((int)(capacity / 0.75 + 1), 0.75f);
    head = new Node(0,0);
    tail = new Node(0,0);
    head.next = tail;
    tail.prev = head;
  }
  public int get(int key) {
    if (map.containsKey(key)) {
      Node cur = map.get(key);
      moveToTail(cur);
      return cur.value;
    }
    return -1;
  }
  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node cur = map.get(key);
      cur.value = value;
      moveToTail(cur);
    } else {
      Node newNode = new Node(key, value);
      if (map.size() == capacity) {
        Node old = removeFirst();
        map.remove(old.key);
      }
      map.put(key, newNode);
      addToTail(newNode);
    }
  }
  private void moveToTail(Node node) {
    // node的左右节点相连
    node.prev.next = node.next;
    node.next.prev = node.prev;
    // node放到最后
    addToTail(node);
  }
  private void addToTail(Node node) {
    node.next = tail;
    node.prev = tail.prev;
    tail.prev.next = node;
    tail.prev = node;
  }
  private Node removeFirst() {
    Node first = head.next;
    head.next = first.next;
    first.next.prev = head;
    return first;
  }
  class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }


  // 方法二 利用linkedHashmap实现
    /*private int capacity;
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }*/
}
