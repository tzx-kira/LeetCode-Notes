package Array;
/*
34.在排序数组中查找元素的第一个和最后一个位置
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
提示：
0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums 是一个非递减数组
-10^9 <= target <= 10^9
 */
public class Solution_34 {
    /*
     * 解题思路：
     * 非递减，有重复元素，时间复杂度有要求O(log n)
     * 考虑二分法，双指针要O(n)
     * 左闭右闭
     * 本题要查找的其实有两个值：目标值在数组中的开始位置和结束位置，所以要分两次二分查找
     * 
     */
    public int[] searchRange(int[] nums, int target) {
        //边界判断
        if(nums.length==0 || target<nums[0] || target>nums[nums.length-1]){
            return new int[]{-1,-1};
        }
        //初始化
        int start=-1;
        int end=-1;
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            //利用右区间端点找开始位置
            int mid=left+(right-left)/2;
            if(nums[mid]>=target){
                right=mid-1;
                start=right;
            }
            else{
                left=mid+1;
            }
        }
        left=0;
        right=nums.length-1;
        while(left<=right){
            //利用左区间端点找结束位置
            int mid=left+(right-left)/2;
            if(nums[mid]<=target){
                left=mid+1;
                end=left;
            }
            else{
                right=mid-1;
            }
        }
        if(end-start>1){
            //因为在找的过程中会多+1或-1
            return new int[]{start+1,end-1};
        }
        else{
            return new int[]{-1,-1};
        }
    }
}
