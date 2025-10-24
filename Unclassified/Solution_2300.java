package Unclassified;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
2300.咒语和药水的成功对数
给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
提示：
n == spells.length
m == potions.length
1 <= n, m <= 10^5
1 <= spells[i], potions[i] <= 10^5
1 <= success <= 10^10
*/
public class Solution_2300 {
    /*
    解题思路：二分查找
    对于每个咒语，计算出与其成功组合所需的最小药水能量强度 minPotion = ceil(success / spell)
    然后在排序后的药水数组 potions 中使用二分查找，找到第一个大于等于 minPotion 的药水位置 index
    由于 potions 已经排序，该位置之后的所有药水都能与当前咒语成功组合，因此成功组合的药水数目为 m - index
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        // 对药水数组进行排序，便于后续二分查找
        Arrays.sort(potions);
        for(int i=0;i<n;i++){
            // 计算与当前咒语成功组合所需的最小药水能量强度
            long minPotion = (success + spells[i] - 1) / spells[i]; // 向上取整
            // 在药水数组中使用二分查找，找到第一个大于等于 minPotion 的药水位置
            int left = 0, right = m - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(potions[mid] < minPotion){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
            // 成功组合的药水数目为 m - left
            res[i] = m - left;
        }
        return res;
    }
}
