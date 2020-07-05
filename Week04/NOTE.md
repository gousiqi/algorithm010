# 第四周
### DFS深度优先搜索
#### 特性
- 属于一种树/图搜索算法
- 只要产生新的节点，就先进行扩展，深入其中，直取结果；
- 能找出所有解决方案
- 缺点：1. 多次遍历搜索可能路径时，标识需要取消；2.深度很大的情况下 效率不够高

#### 代码实现
递归模板：以二叉树的前序遍历为例
``` java
visited = set()
private void dfs(node) {
    if (node == null) {
        return;
    }
    visited.add(node);
    dfs(node.left);
    dfs(node.right);
}
```
迭代模板
``` java
private void dfs(node) {
    if (node == null) {
        return [];
    }
    visited = set() , stack = stack();
    stack.add(node);
    while (!stack.isEmpty()) {
        node = stack.pop();
        visited.add(node);
        process(node);
        nodes = generate_related_nodes(node);
        stack.push(nodes);
    }
}
```

### BFS广度优先搜索
#### 特性
- 树/图搜索算法的一种
- 对于解决最短或最少问题更有优势；
- 优化：双向广度优先算法
- 缺点：内存开销较大，需要不断存储访问状态


#### 代码实现
``` java
private void bfs(node) {
    queue = new LinkedList();
    queue.add(node);
    while (queue not null){
        //若需要先处理一层的元素，进行状态维护，增加一层对queue当前大小的循环遍历
        node = queue.pop();
        visited.add(node);
        process(node);
        nodes = generate_related_nodes(node);
        queue.push(nodes);
    }
}
```

#### 练习题
- [x] 二叉树的层序遍历
- [ ] 最小基因变化
- [x] 括号生成
- [ ] 寻找每个树行中的最大值

### 贪心算法
#### 特性
- 一般用于解决一些最优化问题
- 高效性

#### 适用场景
- 图中的最小生成树 、 哈夫曼编码 、 
- 问题能够分解成子问题，且子问题最优解能够递推到最终问题的最优解 （最优子结构）
- 其余动态规划的不同在于贪心对每个子问题都做出了选择并不能回退；动态规划会保存以前的每个运算结果，并根据以前的结果对当前进行选择，有回退功能；

#### 实战练习题：均在作业题中出现
- [x] 柠檬水找零
- [x] 买卖股票最佳时机II
- [x] 分发饼干
- [x] 模拟行走机器人
- [x] 跳跃游戏
- [ ] 跳跃游戏II

### 二分查找
#### 特性
- 目标函数的单调性
- 上下界
- 索引访问（数组）

#### 代码实现
``` java
left, right = 0, length - 1
while left<=right {
    mid = left + (right - left >> 1)
    if (array[mid] == target) {
        # find the target
        break or return result;
    }
    else if (array[mid] < target) left = mid + 1;
    else right = mid - 1; 
}
```

#### 实战练习题
- [ ] x的平方根
- [ ] 有效的完全平方数
- [x] 搜索旋转排序述组
- [x] 搜索二维矩阵
- [x] 寻找旋转排序树组中的最小值

#### 附件题 
使用二分查找，寻找一个旋转排序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方（返回下标）
注：数组中不存在重复元素

``` java
public int getDisorderIndex(int[] nums) {
    int n = nums.length
    if (n <= 1) return -1;
    int left = 0, right = n - 1;
    while (left < right) { //二分循环，left和right相遇之前跳出
        int mid = left + (right - left >> 1);
        if (nums[mid] < nums[right]) {
            if (nums[mid] < nums[mid-1]) return mid; //去掉有序部分之前，先判断边界点是否为转换点
            right = mid - 1;
        }
        else {
            if (nums[mid] > nums[mid+1]) return mid + 1; //去掉有序部分之前，先判断边界点是否为转换点
            left = mid + 1;   
        }
    }
    return -1;
}
```

### 周作业题
简单
- [x] 柠檬水找零
- [x] 买卖股票最佳时机II
- [x] 分发饼干
- [x] 模拟行走机器人

中等
- [x] 单次接龙
- [x] 岛屿数量
- [x] 扫雷游戏
- [x] 跳跃游戏
- [x] 搜索旋转排序数组
- [x] 搜索二维矩阵
- [x] 寻找旋转排序数组中的最小值

困难
- [ ] 单次接龙II
- [ ] 跳跃游戏II