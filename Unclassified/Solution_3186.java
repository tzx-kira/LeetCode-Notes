package Unclassified;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
3186.施咒的最大总伤害
一个魔法师有许多不同的咒语。
给你一个数组 power ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。
已知魔法师使用伤害值为 power[i] 的咒语时，他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
每个咒语最多只能被使用 一次 。
请你返回这个魔法师可以达到的伤害值之和的 最大值 
提示：
1 <= power.length <= 10^5
1 <= power[i] <= 10^9。
 */
public class Solution_3186 {
    /*
     * 解题思路：
     * 动态规划
     * 统计每个伤害值的出现频率
     * 获取所有唯一的伤害值并排序
     * 不选择当前伤害值x，则最大总伤害值为前一个状态的最大值。
     * 选择当前伤害值x，则需要跳过与x冲突的伤害值（即x - 2到x + 2之间的伤害值），并加上当前伤害值的总伤害值。
     */
    public long maximumTotalDamage(int[] power) {
        // 统计每个伤害值的出现频率
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int p : power) {
            freqMap.merge(p, 1,Integer::sum);
        }
        // 获取所有唯一的伤害值并排序
        int n = freqMap.size();
        int[] uniquePowers = new int[n];
        int index = 0;
        for(int p : freqMap.keySet()) {
            uniquePowers[index++] = p;
        }
        Arrays.sort(uniquePowers);

        // 动态规划数组
        long[] dp = new long[n];
        Arrays.fill(dp, -1); // 初始化为-1，表示未计算
        // 从最后一个伤害值开始递归计算
        return dfs(n - 1, uniquePowers, freqMap, dp);
    }
    private long dfs(int i, int[] uniquePowers, Map<Integer, Integer> freqMap, long[] dp) {
        if(i < 0) {
            return 0; // 基础情况
        }
        if(dp[i] != -1) {
            return dp[i]; // 已经计算过，直接返回结果
        }

        // 不选择当前伤害值
        long notChoose = dfs(i - 1, uniquePowers, freqMap, dp);

        // 选择当前伤害值
        long choose = (long)uniquePowers[i] * freqMap.get(uniquePowers[i]);
        int j = i - 1;
        // 跳过与当前伤害值冲突的伤害值
        while(j >= 0 && uniquePowers[j] >= uniquePowers[i] - 2) {
            j--;
        }
        choose += dfs(j, uniquePowers, freqMap, dp);

        // 取两种选择的最大值
        dp[i] = Math.max(notChoose, choose);
        return dp[i];
    }
}
