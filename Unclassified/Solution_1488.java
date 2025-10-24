package Unclassified;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

/*
1488.避免洪水泛滥
你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨前是空的，那么它就会装满水。如果第 n 个湖泊下雨前是 满的 ，这个湖泊会发生 洪水 。你的目标是避免任意一个湖泊发生洪水。
给你一个整数数组 rains ，其中：
rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
请返回一个数组 ans ，满足：
ans.length == rains.length
如果 rains[i] > 0 ，那么ans[i] == -1 。
如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。
提示：
1 <= rains.length <= 10^5
0 <= rains[i] <= 10^9
 */
public class Solution_1488 {
    /*
     * 解题思路：
     * 如果遇到晴天需要抽水，我们先不着急决策抽哪个湖泊的水，等到后面遇到下雨的湖泊且该湖泊已满再做决策
     * 贪心 + 有序集合
     * 1.使用一个哈希map来记录已经下过雨的湖泊
     * 2.使用一个TreeSet来记录可以抽干湖泊的日子
     * 3.遍历rains数组
     *   - 如果rains[i] > 0，表示第i天有湖泊下雨
     *    - 检查该湖泊是否已经在哈希集合中
     *    - 如果在，说明该湖泊已经满了，需要在之前的某一天抽干它
     *   - 使用有序集合查找一个可以抽干该湖泊的日子
     *   - 如果找到了，更新ans数组，并从有序集合中移除该日子
     *  - 如果没找到，说明无法避免洪水，返回空数组
     *  - 如果不在，将该湖泊加入哈希集合，并将ans[i]设为-1
     *  - 如果rains[i] == 0，表示第i天没有湖泊下雨
     *   - 将该日子加入有序集合，表示可以抽干湖泊
     *  - 最后返回ans数组
     * 
     */
    public int[] avoidFlood(int[] rains) {

        Map<Integer,Integer> fullLakes = new HashMap<Integer,Integer>(); // 记录已经满的湖泊
        TreeSet<Integer> dryDays = new TreeSet<>(); // 记录可以抽干湖泊的日子
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for(int i=0;i<n;i++){

            if(rains[i]>0){
                ans[i] = -1; // 下雨天，ans[i]设为-1
                if(fullLakes.containsKey(rains[i])){
                    Integer dryDay=dryDays.ceiling(fullLakes.get(rains[i])); // 找到一个可以抽干该湖泊的日子
                    if(dryDay==null){
                        return new int[0]; // 无法避免洪水，返回空数组
                    }
                    ans[dryDay] = rains[i]; // 在dryDay这天抽干rains[i]湖泊
                    dryDays.remove(dryDay); // 从可用抽干日子中移除
                }
                fullLakes.put(rains[i],i); // 标记该湖泊为满
                
            }
            else{
                // 如果还有晴天没抽水的情况
                // 随便选个湖泊，比如湖泊 1，抽它
                dryDays.add(i); // 没有湖泊下雨，可以抽干湖泊
                // ans[i] = 1; // 默认抽干湖泊1
            }
        }

        return ans;
    }
}
