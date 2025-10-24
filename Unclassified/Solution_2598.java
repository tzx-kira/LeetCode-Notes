package Unclassified;

/*
题目描述：
2598.执行操作后的最大MEX
给你一个下标从 0 开始的整数数组 nums 和一个整数 value 。
在一步操作中，你可以对 nums 中的任一元素加上或减去 value 。
例如，如果 nums = [1,2,3] 且 value = 2 ，你可以选择 nums[0] 减去 value ，得到 nums = [-1,2,3] 。
数组的 MEX (minimum excluded) 是指其中数组中缺失的最小非负整数。
例如，[-1,2,3] 的 MEX 是 0 ，而 [1,0,3] 的 MEX 是 2 。
返回在执行上述操作 任意次 后，nums 的最大 MEX 。
示例 1：
输入：nums = [1,-10,7,13,6,8], value = 5
输出：4
解释：执行下述操作可以得到这一结果：
- nums[1] 加上 value 两次，nums = [1,0,7,13,6,8]
- nums[2] 减去 value 一次，nums = [1,0,2,13,6,8]
- nums[3] 减去 value 两次，nums = [1,0,2,3,6,8]
nums 的 MEX 是 4 。可以证明 4 是可以取到的最大 MEX 。

提示：
1 <= nums.length, value <= 10^5
-10^9 <= nums[i] <= 10^9
 */
public class Solution_2598 {
    /*
     * 解题思路：
     * 哈希表 + 模运算
     * 1. 计算每个数对value取模的结果，并统计每个结果的出现次数
     * 2. 从0开始，依次检查每个非负整数是否可以通过加减value得到
     * 3. 如果某个数的出现次数为0，说明这个数无法通过加减value得到，返回这个数作为MEX
     * 4. 如果某个数的出现次数大于0，说明这个数可以通过加减value得到，继续检查下一个数
     * 5. 重复步骤3和4，直到找到第一个无法通过加减value得到的数
     * 6. 返回这个数作为最大MEX
     * 时间复杂度：O(n)，其中n是数组nums的长度。我们需要遍历数组一次来统计每个数对value取模的结果，之后最多遍历value次来找到最大MEX。
     * 空间复杂度：O(value)，用于存储每个数对value取模的结果的出现次数。
     */
    public int findSmallestInteger(int[] nums, int value) {
        int[] count = new int[value];
        int n= nums.length;
        for(int i=0;i<n;i++){
            int mod = ((nums[i] % value) + value) % value; // 处理负数取模
            count[mod]++;
        }
        int mex = 0;
        while(true){
            int mod = mex % value;
            if(count[mod] == 0){
                return mex;
            }
            count[mod]--;
            mex++;
        }
        
    }
}
