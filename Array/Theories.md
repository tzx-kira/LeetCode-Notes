# 数组

## 基础

**列表的定义：一种数据项构成的有限序列，即按照一定的线性顺序，排列而成的数据项的集合。**

**数组的定义：数组是存放在连续内存空间上的相同类型数据的集合，是列表的实现方式之一。**

数组可以方便的通过下标索引的方式获取到下标对应的数据。

下面是一个字符数组的例子：

![1758697100282](image/Theories/1.png)

读取数组中的元素，是通过访问索引的方式来读取的。

二维数组如下图所示：

![1758718670941](image/Theories/2.png)

![1758721085233](image/Theories/3.png)

需要注意的地方：

* 数组下标都是从0开始的。
* 数组内存空间的地址是连续的（C++），在删除或者增添元素的时候，需要移动其他元素的地址。
* 数组的元素是不能删的，只能覆盖。

## 常用方法

### 二分法

二分法的两个前提：

1. 数组为有序数组
2. 数组中无重复元素

循环不变量规则：要在二分查找的过程中，保持不变量，就是在while寻找中每一次边界的处理都要坚持根据区间的定义来操作。

写二分法，区间的定义一般为两种，左闭右闭即[left, right]，或者左闭右开即[left, right)。

二分法第一种写法：左闭右闭[left, right]

* while (left <= right) 要使用 <= ，因为left == right是有意义的，所以使用 <=
* if (nums[middle] > target) right 要赋值为 middle - 1，因为当前这个nums[middle]一定不是target，那么接下来要查找的左区间结束下标位置就是 middle - 1

```cpp
// 版本一
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1; // 定义target在左闭右闭的区间里，[left, right]
        while (left <= right) { // 当left==right，区间[left, right]依然有效，所以用 <=
            int middle = left + ((right - left) / 2);// 防止溢出 等同于(left + right)/2
            if (nums[middle] > target) {
                right = middle - 1; // target 在左区间，所以[left, middle - 1]
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，所以[middle + 1, right]
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值，直接返回下标
            }
        }
        // 未找到目标值
        return -1;
    }
};
```

二分法第二种写法：左闭右开[left, right)

* while (left < right)，这里使用 < ,因为left == right在区间[left, right)是没有意义的
* if (nums[middle] > target) right 更新为 middle，因为当前nums[middle]不等于target，去左区间继续寻找，而寻找区间是左闭右开区间，所以right更新为middle，即：下一个查询区间不会去比较nums[middle]

```cpp
// 版本二
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size(); // 定义target在左闭右开的区间里，即：[left, right)
        while (left < right) { // 因为left == right的时候，在[left, right)是无效的空间，所以使用 <
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle; // target 在左区间，在[left, middle)中
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，在[middle + 1, right)中
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值，直接返回下标
            }
        }
        // 未找到目标值
        return -1;
    }
};
```

时间复杂度：O(log n)

空间复杂度：O(1)

### 双指针法

定义一快一慢指针，两个指针从左边或右边开始遍历，快指针负责寻找新数组的元素，慢指针负责更新新数组的下标位置

定义一左一右指针，从两边开始向中间遍历

固定数组中的某一个元素，用双指针去遍历其他元素

### 滑动窗口

所谓滑动窗口，就是不断的调节子序列的起始位置和终止位置，从而得出我们想要的结果 。

使用时需要确定三点：

* 窗口内是什么？
* 如何移动窗口的起始位置？
* 如何移动窗口的结束位置？

### 模拟

按照题目给出的过程进行遍历

注意在遍历时要遵循循环不变量原则

### 前缀和

注意：在使用前缀和求解的时候，要特别注意求解区间。例如，如果要求区间下标 [2, 5] 的区间和，那么应该是 p[5] - p[1]，而不是 p[5] - p[2]。

## 扩展

在C++中，vector的底层实现是array，严格来讲vector是容器，不是数组。

Java是没有指针的，不对程序员暴露其元素的地址，所以应该不是连续的内存地址。
