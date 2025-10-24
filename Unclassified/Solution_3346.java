package Unclassified;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/*
题目描述：
3346.执行操作后元素的最高频率I
给你一个整数数组 nums 和两个整数 k 和 numOperations 。
你必须对 nums 执行 操作  numOperations 次。每次操作中，你可以：
选择一个下标 i ，它在之前的操作中 没有 被选择过。
将 nums[i] 增加范围 [-k, k] 中的一个整数。
在执行完所有操作以后，请你返回 nums 中出现 频率最高 元素的出现次数。
一个元素 x 的 频率 指的是它在数组中出现的次数。

提示：
1 <= nums.length <= 105
1 <= nums[i] <= 105
0 <= k <= 105
0 <= numOperations <= nums.length

 */
public class Solution_3346 {
    /*
     * 解题思路：
     * 差分
     * 每个 nums[i] 在一次操作后可变为区间 [nums[i]-k, nums[i]+k] 内的任意整数。
     * 对某个目标值 x，被覆盖到 x 的元素数量为 coverage(x)（即有多少区间包含 x）。
     * 原本等于 x 的元素不需要操作，其他要变为 x 每个需 1 次操作，且最多只能用 numOperations 次操作。
     * 因此对 x 最终能得到的频率为 min( coverage(x), countEqualX + numOperations )。
     * coverage(x) 仅在区间端点发生变化，使用差分（扫描线）在所有事件点与原数组值处枚举 x 即可。
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        if (n == 0) return 0;

        // 统计原始相等值的频次，并记录初始最大频次（无操作时的答案下界）
        Map<Integer, Integer> freq = new HashMap<>();
        int baseMax = 0;
        for (int v : nums) {
            int c = freq.getOrDefault(v, 0) + 1;
            freq.put(v, c);
            baseMax = Math.max(baseMax, c);
        }

        // 差分事件：区间起点 a 加 1，终点 b+1 减 1（使用 long 避免越界）
        TreeMap<Long, Integer> events = new TreeMap<>();
        for (int v : nums) {
            long a = (long) v - k;
            long b = (long) v + k;
            events.put(a, events.getOrDefault(a, 0) + 1);
            events.put(b + 1, events.getOrDefault(b + 1, 0) - 1);
        }

        // 合并需要检查的位置：所有事件点与所有原数组值
        TreeSet<Long> positions = new TreeSet<>(events.keySet());
        for (int v : freq.keySet()) positions.add((long) v);

        long curr = 0; // 当前覆盖数
        int ans = baseMax; // 初始答案至少为原数组中某值的最大出现次数
        for (long pos : positions) {
            // 在 pos 应用所有事件（区间在起点包含，在 b+1 不包含）
            if (events.containsKey(pos)) curr += events.get(pos);
            int coverage = (int) curr; // coverage <= n

            int countEqual = 0;
            if (pos >= Integer.MIN_VALUE && pos <= Integer.MAX_VALUE) {
                countEqual = freq.getOrDefault((int) pos, 0);
            }
            // 最终能得到的频率
            int finalFreq = Math.min(coverage, countEqual + numOperations);
            if (finalFreq > ans) ans = finalFreq;
        }
        return ans;
    }
}
