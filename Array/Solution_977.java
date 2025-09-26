package Array;
/*
977.有序数组的平方
给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
提示：
1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums 已按 非递减顺序 排序
请你设计时间复杂度为 O(n) 的算法解决本问题

*/
public class Solution_977 {
    /*
     * 解题思路：
     * 双指针
     * 左指针left指向数组开头，右指针right指向数组结尾
     * 由于数组是非递减排序，平方后，绝对值最大的数的平方值最大
     * 比较nums[left]和nums[right]的绝对值，绝对值大的平方值放在结果数组的最后面
     * 然后将对应的指针向中间移动
     * 重复上述过程，直到left>right
     * 由于结果数组是从后往前放值的，所以结果数组的索引要从length-1开始递减
     * 时间复杂度O(n)
     */
    public int[] sortedSquares(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int[] result=new int[nums.length];
        int index=nums.length-1;
        while(left<=right){
            if(Math.abs(nums[left])>Math.abs(nums[right])){
                result[index]=nums[left]*nums[left];
                left++;
            }
            else{
                result[index]=nums[right]*nums[right];
                right--;
            }
            index--;
        }
        return result;
    }
}
