# 第三周
## 泛型递归/树的递归 Recursion
### 递归的实现
1. 递归的本质就是循环，只是通过函数体来进行循环
2. 代码实现
``` java
public void recur(int val, int param) {
    //1. terminator
    if(level>MAX_LEVEL) {
        //process result
        return;
    }
    
    //2. process current logic
    process(level, param);
    //3. drill down
    recur(level+1, newParam);
    
    //4. restore current status if needed.
}
```

3. 经典题：

   [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

   题解：

   ```java
   class Solution {
       public int maxDepth(TreeNode root) {
           //递归 子树的层数+1；
           if (root == null) return 0;
         	int left = maxDepth(root.left); //左子树的最大深度
         	int right = masDepth(root.right); //右子树的最大深度
           return Math.max(left,right) + 1;
       }
   }
   ```

   [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

   题解:

   ```java
   class Solution {
       public int minDepth(TreeNode root) {
           //递归 注意这里的最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
           // 叶子节点是指左右子节点都为空的情况 若有左无右或者有右无左，都不算叶子节点。
           if (root == null) return 0;
           int left = minDepth(root.left);
           int right = minDepth(root.right);
           return (root.left != null && root.right != null) ? Math.min(left,right) + 1 : Math.max(left,right) + 1;
       }
   }
   ```

   

### 递归的特性

- 有一个明确的结束条件
- 每次进入下一层时，问题规模相比上一次会减小
- 若递归存在分支，会导致效率低下甚至栈溢出

### 递归的思维要点
- 找最近重复性
- 避免人肉递归
- 数学归纳法思维

### 相关习题：
- [x] 爬楼梯
- [x] 括号生成
- [ ] 反转二叉树
- [ ] 验证二叉搜索树
- [x] 二叉树的最大深度
- [x] 二叉树的最小深度
- [ ] 二叉树的序列化与反序列化


## 分治/回溯
### 分治算法特性
- 分治算法就是将原问题划分成n个规模较小，结构与原问题相似的子问题；
- 一般是通过递归来解决子问题，然后合并结果
- 但凡能够使用数学归纳法解决的问题，都可以使用分治的思想
- 分治思想也不一定使用递归结构

### 分治算法实现模板
1. 代码模板
``` python
# Python
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
  if problem is None: 
	print_result 
	return 
  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 
  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …
  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
```
2. 经典题：[括号生成](https://leetcode-cn.com/problems/generate-parentheses/)

   - 描述：

     数字 *n* 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

   - 题解：

     ```java
     class Solution {
         List<String> result = new ArrayList<>();
         public List<String> generateParenthesis(int n) {
             // 抽象成2n个格子 放入左右括号，合法的可能性
             // 第一步 先写函数 ，n指剩余的左右括号数量，s表示有效的答案
             _generate(n, n, "");
             return result;
         }
     
         private void _generate(int left, int right, String s) {
             //terminate
             if (left == 0 && right == 0) {
                 result.add(s);// combine前提
                 return;
             }
             // process logic 保证左括号不能超过n个，左括号个数>右括号
             // drill down
             if (left > 0) {
                 _generate(left -1, right, s + '(');
             }
             if (left < right ) {
                 _generate(left, right -1, s + ')');
             }
             // reverse status
         }
     }
     ```

3. 回溯经典题： 全排列

   ```java
   class Solution {
       List<List<Integer>> result = new ArrayList<>();
       public List<List<Integer>> permute(int[] nums) {
           if (nums.length == 0) return result;
           dfs(nums.length, nums, new ArrayList<>(), new int[nums.length]);
           return result;
       }
       //回溯思想：每次选择一个元素，回溯后再下一次遍历，tags标记已添加过的元素，进入下一次选择，一共选择nums.length次
       private void dfs(int capacity, int[] nums, List<Integer> res , int[] tags) {
           //terminator
           if (capacity == 0) {
               result.add(new ArrayList<>(res));
               return;
           }
           for (int i = 0; i < nums.length; i++) { //process & drill down
               if (tags[i] == 1) continue; //判断是否已添加过
               tags[i] = 1;
               res.add(nums[i]);
               dfs(capacity-1, nums, res, tags);
               tags[i] = 0;  // backtrace
               res.remove(res.size()-1);
           }
       }
   }
   ```

   

### 回溯算法特性

- 尝试法试错
- 通常也是用最简单的递归方式实现
- 找到可能存在的一个或者多个答案
- 最坏的情况下，回溯算法可能导致复杂度为指数时间的计算
- 代码模板类似递归

### 相关习题
1. 分治
- [x] 括号生成
- [x] Pow(x, n) 
- [x] [子集](https://leetcode-cn.com/problems/subsets/)
- [ ] [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
- [ ] [多数元素](https://leetcode-cn.com/problems/majority-element/)

2. 回溯
- [ ] N皇后问题
- [ ] 数独


### 附加：牛顿迭代法
1. **牛顿迭代法快速寻找平方根**
下面这种方法可以很有效地求出根号a的近似值：首先随便猜一个近似值x，然后不断令x等于x和a/x的平均数，迭代个六七次后x的值就已经相当精确了。例如，我想求根号2等于多少。假如我猜测的结果为4，虽然错的离谱，但你可以看到使用牛顿迭代法后这个值很快就趋近于根号2了：

- (       4  + 2/   4     ) / 2 = 2.25
- (    2.25  + 2/   2.25  ) / 2 = 1.56944..
- ( 1.56944..+ 2/1.56944..) / 2 = 1.42189..
- ( 1.42189..+ 2/1.42189..) / 2 = 1.41423..
….
       
    这种算法的原理很简单，我们仅仅是不断用(x,f(x))的切线来逼近方程x^2 - a=0的根。根号a实际上就是x^ 2-a=0 的一个正实根，这个函数的导数是2x。 也就是说，函数上任一点(x,f(x))处的切线斜率是2x。那么，x-f(x)/(2x)就是一个比x更接近的近似值。代入f(x)=x^2 - a得到x-(x^2 - a)/(2x)，也就是(x+a/x)/2。
    同样的方法可以用在其它的近似值计算中。

2. 代码：
``` java
int mysqrt(int x){
	double tmpx = x;
	double k = 1.0;
	double k0 = 0.0;
	while(abs(k0-k) >= 1){
		k0 = k;
		k = (k + tmpx/k)/2;
	}
	return (int)k;
}
```

### 周作业题：
- [x] 二叉树最近公共祖先
- [x] 从前序中序遍历构造二叉树
- [x] 组合
- [x] 全排列
- [ ] 全排列II