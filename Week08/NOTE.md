# 第八周
## 位运算
### 基本的位运算符
- '<<' 左移 乘2
- '>>' 右移 除2
- | 按位或 （有一则为1）
- & 按位与 （有0则为0）
- ~ 按位取反 （1为0 0为1）
- ^ 按位异或 （相同为0 不同为1）

#### 异或的一些特点：
- x ^ 0 = x
- x ^ 1s = ~x
- (x ^ ~x) = 1s and (x ^ x) = 0
- ^ 满足交换律结合律 

#### 指定位置的位运算
- 左边n位清零： x & (~0 << n)
- 获取x的第n位值： (x >> n) & 1
- 获取x的第n次幂： x & (1 << n)
- x的第n位替换为1： x | (1 << n)

#### 常用技巧：
- 判断奇偶： (x & 1) == 0 or 1
- 乘除2 >>1 <<1
- 清零最低位的1： x & (x - 1)
- 得到最低位的1： x & -x
### 经典题
- 10进制转2进制：递归除以2，余数拼接后，逆序
- [x] N皇后
- [x] N皇后II

## 布隆过滤器
### 核心实现
- 由一个超大的位数组和几个随机哈希函数实现；
- 添加元素操作：
    1. 将添加元素执行k个哈希函数得到k个哈希值
    2. 将位数组对应k个位置设为1
- 查找元素
    1. 将目标元素执行k个哈希函数得到k个哈希值
    2. 判断位数组这k个位置上是否均为1
    3. 若非均为1，则肯定不存在； 若均为1，**可能存在**
### 特点
- 空间效率和查询效率极高
- 有一定的误识别率
### 应用场景
- 比特币网络
- 分布式系统
- Redis缓存
- 垃圾邮件过滤

## LRU缓存
### 代码实现
- 此处给出一个常规实现：数组+双向链表
```java
class Solution {
  private HashMap<Integer, Node> map;
  private final int capacity;
  private Node head;
  private Node tail;
  // 初始化。注意：链表最好先定义头节点，双向链表先定义头尾节点；
  public LRUCache(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>((int)(capacity / 0.75 + 1), 0.75f);
    head = new Node(0,0);
    tail = new Node(0,0);
    head.next = tail;
    tail.prev = head;
  }
  // 具体方法实现略 get时将返回的元素防止末尾；put时若超过容量需要将头部元素挤出
}
// 双向链表定义
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
```

## 排序算法
### 快速排序实现
```java
class QuickSort{
  public static void quickSort(int[] nums, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(nums, begin, end);
    quickSort(nums, begin, pivot - 1);
    quickSort(nums, pivot + 1, end);
  }
    
  // 定义标杆位置的下标，划分后返回新的下标
  private static int partition(int[] nums, int begin, int end) {
    int pivot = end, count = begin;
    for (int i = begin; i < end; i++) {
      if (nums[i] < nums[pivot]) {
        swap(nums, i, count);
        count++;
      }
    }
    swap(nums, pivot, count);
    return count;
  }
  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }
  public static void main(String[] args){
    int[] test = new int[]{2,4,1,4,7,8,3,5};
    quickSort(test, 0, 7);
  }
}
```

### 归并排序
```java
class MergeSort{
  public static void mergeSort(int[] nums, int left, int right) {
    if (right <= left) return;
    int mid = left + (right - left >> 1);
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    crossSort(nums, left, mid, right);
  }
  private static void crossSort(int[] nums, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;
    while (i <= mid && j <= right) {
      if (nums[i] < nums[j]) temp[k++] = nums[i++];
      else temp[k++] = nums[j++];
    }
    while (i <= mid) temp[k++] = nums[i++];
    while (j <= right) temp[k++] = nums[j++];
    for (int m = 0; m < temp.length; m++) {
      nums[left + m] = temp[m];
    }
  }
}
```

### 例题
- [x] 翻转对
- [ ] 数组的相对排序
- [x] 合并区间