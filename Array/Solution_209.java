package Array;
/*
209.长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。
找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
提示：
1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104
进阶：
如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class Solution_209 {
    /*
    解题思路：
    滑动窗口
    定义两个指针left和right，分别表示子数组的起始位置和终止位置
    初始时，left和right都指向数组的开头
    定义一个变量sum，表示当前子数组的和，初始值为0
    定义一个变量minLength，表示满足条件的子数组的最小长度，初始值为Integer.MAX_VALUE
    不断移动right指针，扩大子数组的范围，同时将nums[right]加入sum
    当sum大于等于target时，说明当前子数组满足条件，更新minLength为当前子数组的长度right-left+1
    然后移动left指针，缩小子数组的范围，同时将nums[left]从sum中减去
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLength=Integer.MAX_VALUE;
        int sum=0;
        for(int left=0,right=0;right<nums.length;right++){
            sum+=nums[right];
            while(sum>=target){
                minLength=Math.min(minLength,right-left+1);
                sum-=nums[left];
                left++;
            }
        }
        return minLength==Integer.MAX_VALUE?0:minLength;
    }
}
