# 第一周

## 数据结构

### 1. 按存储结构

#### 顺序存储：数组

#### 链式存储：链表，跳表

1. skip list提升链表的访问效率（log n），但牺牲了维护成本，增删改都要维护索引；
2. redis使用skip list实现，**学习redis源码**

#### 实战题目：

* [x] **LRU缓存**——*利用hash表和双向链表（手动实现双向链表）；或者手动实现LinkedHashmap*

* [x] 移动零——*双指针，x指针负责遍历，y指针负责定位最靠前的0值点，每次x遍历到的非零值与y值替换；*

* [x] 盛水最多容器——*双指针，头尾中间靠拢，合理牺牲宽度换取高度*
* [x] 爬楼梯——*递归重复访问严重，效率极低，考虑递归时将历史结果缓存到数组中，或直接使用一重循环，每次遍历将结果保存；*
* [ ] **3数之和**
* [ ] 环形链表

### 2. 按逻辑结构-线性

#### 栈 

1. O(1)出入栈
2. 分析Stack源码（核心方法push(E item)/pop()/peek()）：

#### 队列 

1. O(1)出入队列；
2. **双端队列Deque**，头尾均可出入队；
3. 分析Queue（具体分析Deque的实现）源码（核心方法）；
4. **优先队列PriorityQueue**，取出按优先级获取；可用于实现获取第K大的元素的数据结构

#### 实战题目

* [ ] 有效的括号
* [ ] 最小栈

#### 课后练习：分析Queue及实现类PriorityQueue源码

##### Queue （Interface）

1. **定义** 

Queue是jdk1.5新增的数据结构，根据文档中的定义：

* * *

`
A collection designed for holding elements prior to processing. Besides basic Collection operations, queues provide additional insertion, extraction, and inspection operations. 
`

* * *

Queue是Collection的一种，与Set、List的区别在于Queue主要用于存储数据，而不是处理数据。在，Queue提供了额外的插入、删除、查询操作。

2. **API**

Queue定义了增删查三种操作，每种操作都有两种形式，一种在操作失败时抛出异常，另一种返回特殊值：

|      | 抛出异常  | 返回特殊值 |
| ---- | --------- | ---------- |
| 插入 | add(e)    | offer(e)   |
| 删除 | remove()  | poll()     |
| 查询 | element() | peek()     |

offer(e)是插入一个元素，插入失败返回false，用于一些正常情况可能插入失败的场景，比如有边界的queue；
remove()和poll()都是删除并返回队列的头部元素，删除哪个取决于队列中元素的排列顺序，跟具体实现有关；队列为空时poll()返回null；
element()和peek()只返回队列头部元素，但不删除；
*Queue中未定义并发编程所需的阻塞队列相关api，比如等待元素可见或等待队列空间可用，都在子类接口BlockingQueue中进行实现，此处不作分析*

##### PriorityQueue (C)

1. **定义**

根据文档中的定义：

* * *

`An unbounded priority queue based on a priority heap. The elements of the priority queue are ordered according to their natural ordering, or by a Comparator provided at queue construction time, depending on which constructor is used. A priority queue does not permit null elements. A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so may result in ClassCastException).`

* * *

PriorityQueue是基于优先级堆实现的无界优先级队列。队列中的元素根据自然顺序排序，或者通过构造时提供的比较器来排序。队列中不可插入空元素，同样也不能插入不可比较的对象（ClassCastException）

* * *

`The head of this queue is the least element with respect to the specified ordering. If multiple elements are tied for least value, the head is one of those elements -- ties are broken arbitrarily. The queue retrieval operations poll, remove, peek, and element access the element at the head of the queue.`

* * *

队列头部是与指定顺序相关的最小元素。队列的检索操作（删除、查询）都是从头部元素开始的。
队列虽无界但也有容量，容量会随着元素插入自动增长。（Arrays.copyOf(queue, newCapacity);）
PriorityQueue不是线程同步的，对应的有线程安全的PriorityBlockingQueue；

2. **成员属性**

PriorityQueue中定义的成员属性如下：

```
//队列的默认初始容量
private static final int DEFAULT_INITIAL_CAPACITY = 11；

/**
* 优先级队列内部使用数组来存储元素
* 实际上，此数组使用了堆排序的方式构建小根堆，使队列的头部总是最小的元素
**/
transient Object[] queue; 

//队列中的元素个数
private int size = 0;

//元素排序使用的比较器，如果comparator为null，则使用自然排序
private final Comparator<? super E> comparator;

//记录队列结构修改的次数，当队列中有元素插入、移除或者删除时，队列的结构会发生变化
transient int modCount = 0; 

//数组的最大长度
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
```

3. **构造方法**

类中提供了7种方式来构造优先队列，初始容量、比较器、给定集合（或给定优先队列、给定SortedSet）均可以在构造时进行指定，详见构造器的源码

4. **成员方法**
   因为优先队列是基于优先级堆实现的，所以相较于普通队列，其核心操作在于根据比较器进行最小堆的调整。
   PriorityQueue的成员方法包括下面三类：

* 插入方法
  提供了add(E e),offer(E e)两个方法，实际前者也直接调用了后者

```
/**
 * Inserts the specified element into this priority queue.
 */
public boolean add(E e) {
    return offer(e);
}

/**
 * Inserts the specified element into this priority queue.
 */
public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    if (i >= queue.length)
        //扩容
        grow(i + 1);
    size = i + 1;
    if (i == 0)
        queue[0] = e;
    else
        //将e放在数组的最后一个位置，向上调整最小堆queue[]，具体实现后面详述；
        siftUp(i, e);
    return true;
}
```

* 移除方法
  PriorityQueue的移除操作提供了poll(),remove(Object o),removeEq(Object o)
  poll()移除队列头部元素，若队列为空直接返回null；
  remove(Object o)若队列中个存在给定的元素o，通过for循环确定o的下表然后删除，该方法是public的
  removeEq(Object o)该方法是default只能再同包的类中使用，主要供PriorityQueue的迭代器调用

```
//移除队列头部元素
public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        //将末尾元素取出，从头部向下调整最小堆
        siftDown(0, x);
    return result;
}

/**
 * Removes a single instance of the specified element from this queue,
 */
public boolean remove(Object o) {
    int i = indexOf(o);
    //不存在直接返回
    if (i == -1)
        return false;
    else {
        //从队列中删除元素o，具体实现后述
        removeAt(i);
        return true;
    }
}

/**
 * Version of remove using reference equality, not equals.
 * Needed by iterator.remove.
 */
boolean removeEq(Object o) {
    //通过遍历的方式删除可能存在的指定元素
    for (int i = 0; i < size; i++) {
        if (o == queue[i]) {
            removeAt(i);
            return true;
        }
    }
    return false;
}
```

* 公共方法
  公共方法中主要的有如下几个
  通过Collection进行构造有限队列的方法：

```
//通过给定的优先级队列进行构造
private void initFromPriorityQueue(PriorityQueue<? extends E> c) {
    if (c.getClass() == PriorityQueue.class) {
        this.queue = c.toArray();
        this.size = c.size();
    } else {
        initFromCollection(c);
    }
}

//给定集合中的元素进行构造（无堆排序部分，若传入的非sortedSet，在heapify()中进行堆排序）
private void initElementsFromCollection(Collection<? extends E> c) {
    Object[] a = c.toArray();
    // If c.toArray incorrectly doesn't return Object[], copy it.
    // 若返回的不是数组，则复制
    if (a.getClass() != Object[].class)
        a = Arrays.copyOf(a, a.length, Object[].class);
    int len = a.length;
    if (len == 1 || this.comparator != null)
        for (int i = 0; i < len; i++)
            if (a[i] == null)
                throw new NullPointerException();
    this.queue = a;
    this.size = a.length;
}

//给定非sortedSet元素进行构造
private void initFromCollection(Collection<? extends E> c) {
    initElementsFromCollection(c);
    //向下构建最小堆（堆排序）
    heapify();
}
```

向上、向下的堆排序（构造最小堆）

```
//自然排序从k节点向上构造
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        //计算第k个节点的父节点下标
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        //如果x大于第k个节点的父节点，说明已是最小堆，跳出循环
        if (key.compareTo((E) e) >= 0)
            break;
        //交换k节点与父节点元素
        queue[k] = e;
        k = parent;
    }
    //将元素x放在第k个节点位置
    queue[k] = key;
}
//使用比较器向上构造
private void siftUpUsingComparator(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}

//自然排序向下构造
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        //计算第k个节点的左孩子节点下标，为2k+1
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        //计算第k个节点的右孩子节点下标，为2k+2
        int right = child + 1;
        //获取左右孩子的最小节点
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        //若key小于c说明已经是最小堆，跳出
        if (key.compareTo((E) c) <= 0)
            break;
        //左右子节点中最小的节点放在第k个节点位置，
        queue[k] = c;
        k = child;
    }
    //将元素x放在第k个节点位置
    queue[k] = key;
}
//使用比较器向下构造
private void siftDownUsingComparator(int k, E x) {
    int half = size >>> 1;
    while (k < half) {
        int child = (k << 1) + 1;
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            comparator.compare((E) c, (E) queue[right]) > 0)
            c = queue[child = right];
        if (comparator.compare(x, (E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = x;
}
```

根据给定下标删除元素

```
private E removeAt(int i) {
    // assert i >= 0 && i < size;
    modCount++;
    int s = --size;
    if (s == i) // removed last element
        queue[i] = null;
    else {
        E moved = (E) queue[s];
        queue[s] = null;
        siftDown(i, moved);
        //数组最后一个元素被直接移动到了被删除元素的位置时，重新向上调整
        if (queue[i] == moved) {
            siftUp(i, moved);
            if (queue[i] != moved)
                return moved;
        }
    }
    return null;
}
```

将队列序列化至输出流中；输入流中反序列化出队列，此处贴出源码，不具体分析

```
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException {
    // Write out element count, and any hidden stuff
    s.defaultWriteObject();

    // Write out array length, for compatibility with 1.5 version
    s.writeInt(Math.max(2, size + 1));

    // Write out all elements in the "proper order".
    for (int i = 0; i < size; i++)
        s.writeObject(queue[i]);
}

private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    // Read in size, and any hidden stuff
    s.defaultReadObject();

    // Read in (and discard) array length
    s.readInt();

    SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, size);
    queue = new Object[size];

    // Read in all elements.
    for (int i = 0; i < size; i++)
        queue[i] = s.readObject();

    // Elements are guaranteed to be in "proper order", but the
    // spec has never explained what that might be.
    heapify();
}
```

总结来说：

* PriorityQueue是无界的，不限制容量
* PriorityQueue不允许插入空元素，如有比较器，插入元素按比较器排序，否则按自然顺序
* PriorityQueue线程不安全
* PriorityQueue优先级是借助内部数组的堆排序实现的，任何的插入删除操作，会进行堆调整以重新构建最小堆，所以插入删除时间复杂度均为O(log n)

