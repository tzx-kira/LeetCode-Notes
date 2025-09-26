package Array;
/*
704.二分查找
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
你必须编写一个具有 O(log n) 时间复杂度的算法。

提示：
你可以假设 nums 中的所有元素是不重复的。
n 将在 [1, 10000]之间。
nums 的每个元素都将在 [-9999, 9999]之间。
*/

/*
解题思路：
二分查找
本题满足二分查找的两个前提：
1.数组为有序数组
2.数组中无重复元素
*/
class Solution_704 {
    //二分法左闭右闭
    public int search(int[] nums, int target) {
        //边界判断
        if(target<nums[0] || target>nums[nums.length-1]){
            return -1;
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
        return -1;
    }

    //二分法左闭右开
    public int search_2(int[] nums, int target) {
        //边界判断
        if(target<nums[0] || target>nums[nums.length-1]){
            return -1;
        }
        //初始化
        int left=0;
        int right=nums.length;
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
        return -1;
    }
}

