package Unclassified;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Arrays;
/*
题目描述：
3347.执行操作后元素的最高频率II
给你一个整数数组 nums 和两个整数 k 和 numOperations 。
你必须对 nums 执行 操作  numOperations 次。每次操作中，你可以：
选择一个下标 i ，它在之前的操作中 没有 被选择过。
将 nums[i] 增加范围 [-k, k] 中的一个整数。
在执行完所有操作以后，请你返回 nums 中出现 频率最高 元素的出现次数。
一个元素 x 的 频率 指的是它在数组中出现的次数。
示例 1：
输入：nums = [1,4,5], k = 1, numOperations = 2
输出：2
解释：
通过以下操作得到最高频率 2 ：
将 nums[1] 增加 0 ，nums 变为 [1, 4, 5] 。
将 nums[2] 增加 -1 ，nums 变为 [1, 4, 4] 。
提示：
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= k <= 10^9
0 <= numOperations <= nums.length
 */
public class Solution_3347 {
    /*
     * 解题思路：
     * 差分
     * 每个元素 nums[i] 在操作后可达到的值为区间 [nums[i]-k, nums[i]+k]（整数）。
     * 对某个目标值 x，能够变成 x 的元素数量为覆盖 x 的区间数 coverage(x)。
     * 其中原本等于 x 的元素不需要操作，其余需要 1 次操作/元素，且总操作次数不能超过 numOperations。
     * 因此对于 x 最终可达频率为 min( coverage(x), countEqualX + numOperations )。
     * coverage(x) 在区间端点处发生变化，所以仅需在所有区间起点/终点及原数组值上扫描。
     * 采用差分（扫描线）统计 coverage，并在每个候选 x 处计算上面的公式，取最大值。
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        if (n == 0) return 0;

        // 统计原始相等值的频次
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : nums) freq.put(v, freq.getOrDefault(v, 0) + 1);

        // 差分事件：在 a 处 +1，在 b+1 处 -1 （使用 long 避免越界）
        TreeMap<Long, Integer> events = new TreeMap<>();
        for (int v : nums) {
            long a = (long) v - k;
            long b = (long) v + k;
            events.put(a, events.getOrDefault(a, 0) + 1);
            // b+1 可能超出 int 范围 => 用 long
            long endNext = b + 1;
            events.put(endNext, events.getOrDefault(endNext, 0) - 1);
        }

        // 将所有候选位置（事件位置 + 原数组值）合并为有序集合
        TreeSet<Long> positions = new TreeSet<>(events.keySet());
        for (int v : freq.keySet()) positions.add((long) v);

        long curr = 0; // 当前覆盖数
        int ans = 1;
        // 遍历所有候选位置（按升序）
        for (long pos : positions) {
            // 先应用事件（在 pos 的 +1/-1），之后 curr 即为覆盖 pos 的区间数
            if (events.containsKey(pos)) curr += events.get(pos);
            int coverage = (int) curr; // coverage 不会超过 n

            int countEqual = 0;
            if (pos >= Integer.MIN_VALUE && pos <= Integer.MAX_VALUE) {
                countEqual = freq.getOrDefault((int) pos, 0);
            }
            // 能得到的最大频率
            int finalFreq = Math.min(coverage, countEqual + numOperations);
            if (finalFreq > ans) ans = finalFreq;
            // 另外，当 countEqual == 0 时 finalFreq == min(coverage, numOperations)，已包含
        }
        return ans;

    }
}
