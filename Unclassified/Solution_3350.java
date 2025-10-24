package Unclassified;

import java.util.List;

/*
题目描述：
3350.检测相邻递增子数组||
给你一个由 n 个整数组成的数组 nums ，请你找出 k 的 最大值，使得存在 两个 相邻 且长度为 k 的 严格递增 子数组。具体来说，需要检查是否存在从下标 a 和 b (a < b) 开始的 两个 子数组，并满足下述全部条件：
这两个子数组 nums[a..a + k - 1] 和 nums[b..b + k - 1] 都是 严格递增 的。
这两个子数组必须是 相邻的，即 b = a + k。
返回 k 的 最大可能 值。
子数组 是数组中的一个连续 非空 的元素序列。

示例 1：
输入：nums = [2,5,7,8,9,2,3,4,3,1]
输出：3
解释：
从下标 2 开始的子数组是 [7, 8, 9]，它是严格递增的。
从下标 5 开始的子数组是 [2, 3, 4]，它也是严格递增的。
这两个子数组是相邻的，因此 3 是满足题目条件的 最大 k 值。
提示：
2 <= nums.length <= 2 * 105
-109 <= nums[i] <= 109
 */
public class Solution_3350 {
    /*
     * 解题思路：
     * 贪心 + 双指针
     * 1. 使用两个指针preCnt和cnt分别记录上一个严格递增段和当前严格递增段的长度
     * 2. 遍历数组，寻找严格递增段，当遇到非严格递增的元素时，计算当前严格递增段和上一个严格递增段的最大k值
     * 3. 更新preCnt为当前严格递增段的长度，重置cnt为0，继续遍历
     * 4. 最终返回找到的最大k值
     */
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int ans = 0;
        int preCnt = 0; //上一个严格递归段
        int cnt = 0; //当前严格递归段
        // 寻找严格递归段
        for (int i = 0; i < nums.size(); i++) {
            cnt++;
            // i 是严格递增段的末尾
            if (i == nums.size() - 1 || nums.get(i) >= nums.get(i + 1)) {
                ans = Math.max(ans, Math.max(cnt / 2, Math.min(preCnt, cnt)));
                preCnt = cnt;
                cnt = 0;
            }
        }
        return ans;


    }
}
