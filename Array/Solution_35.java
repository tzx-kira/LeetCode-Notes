package Array;
/*
35.搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
请必须使用时间复杂度为 O(log n) 的算法。
提示:
1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums 为 无重复元素 的 升序 排列数组
-10^4 <= target <= 10^4
 */

public class Solution_35 {
    /*
     * 解题思路：
     * 二分查找
     * 满足两个条件
     * 左闭右闭写法
     */
    public int searchInsert(int[] nums, int target) {
        //边界判断
        if(target<nums[0]){
            return 0;
        }
        if(target>nums[nums.length-1]){
            return nums.length;
        }
        //初始化
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }
            else if(nums[mid]<target){
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }

        return right+1; //此时left=right+1，right为小于target的最大值
    }

    /*
     * 解题思路：
     * 二分查找
     * 满足两个条件
     * 左闭右开写法
     */
    public int searchInsert_2(int[] nums, int target) {
        //边界判断
        if(target<nums[0]){
            return 0;
        }
        if(target>nums[nums.length-1]){
            return nums.length;
        }
        //初始化
        int left=0;
        int right=nums.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }
            else if(nums[mid]<target){
                left=mid+1;
            }
            else{
                right=mid;
            }
        }

        return right; //此时left=right，right为大于等于target的最小值
    }
}

