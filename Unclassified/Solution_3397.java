package Unclassified;
import java.util.Arrays;
import java.lang.Math;
/*
3397.执行操作后不同元素的最大数量
给你一个整数数组 nums 和一个整数 k。
你可以对数组中的每个元素 最多 执行 一次 以下操作：
将一个在范围 [-k, k] 内的整数加到该元素上。
返回执行这些操作后，nums 中可能拥有的不同元素的 最大 数量。

提示：
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= k <= 10^9
 */
public class Solution_3397 {
    /*
     * 解题思路：
     * 贪心 排序
     * 将数组排序后，遍历数组并使用一个变量 end 来跟踪当前不同元素的最大值。
     * 对于每个元素 num，如果 num 大于 end，则表示可以通过加上一个在范围 [-k, k]内的整数来使其成为一个新的不同元素。
     * 因此，我们将 end 更新为 num + k，并将不同元素的计数器加一。
     * 否则，如果 num 小于等于 end，则表示无法通过加上一个在范围 [-k, k]内的整数来使其成为一个新的不同元素，因此我们跳过该元素。
     * 最终，不同元素的计数器即为执行操作后 nums 中可能拥有的不同元素的最大数量。
     * 
     */
    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int count = 0;
        long end = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int curnum=(int) Math.min(Math.max(nums[i] - k, end + 1), nums[i] + k);

            if (curnum > end) {
                count++;
                end = curnum;
            }
        }
        return count;
    }
}