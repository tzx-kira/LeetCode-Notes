package Array;
/*
27.移除元素
给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
返回 k。

提示：
0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100

 */
public class Solution_27 {
    /*
     * 解题思路：
     * 双指针（通过一个快指针和慢指针在一个for循环下完成两个for循环的工作)
     * 一个指针i遍历数组，另一个指针k记录不等于val的元素个数
     * 当nums[i]!=val时，将nums[i]赋值给nums[k]，并将k++
     * 遍历结束后，k即为不等于val的元素个数
     */
    public int removeElement(int[] nums, int val) {
        int k=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[k]=nums[i];
                k++;
            }
        }
        return k;
    }
}
