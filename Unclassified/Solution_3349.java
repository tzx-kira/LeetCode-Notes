package Unclassified;

import java.util.List;

/*
3349.检测相邻递增子数组
给你一个由 n 个整数组成的数组 nums 和一个整数 k，请你确定是否存在 两个 相邻 且长度为 k 的 严格递增 子数组。具体来说，需要检查是否存在从下标 a 和 b (a < b) 开始的 两个 子数组，并满足下述全部条件：
这两个子数组 nums[a..a + k - 1] 和 nums[b..b + k - 1] 都是 严格递增 的。
这两个子数组必须是 相邻的，即 b = a + k。
如果可以找到这样的 两个 子数组，请返回 true；否则返回 false。
子数组 是数组中的一个连续 非空 的元素序列。
提示：
2 <= nums.length <= 100
1 <= 2 * k <= nums.length
-1000 <= nums[i] <= 1000
 */
public class Solution_3349 {
    /*
    解题思路：
    模拟
    求出两个相邻的长度为k的子数组的起始位置，然后分别判断这两个子数组是否严格递增
     */
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        for(int i=0; i<=n-2*k; i++){
            boolean flag = true;
            for(int j=0; j<k-1; j++){
                if(nums.get(i+j+1)<=nums.get(i+j)){
                    flag = false;
                    break;
                }
            }
            if(!flag) continue;
            for(int j=0; j<k-1; j++){
                if( nums.get(i+k+j+1)<=nums.get(i+k+j)){
                    flag = false;
                    break;
                }
            }
            if(flag) return true;
        }
        return false;
    }
}
