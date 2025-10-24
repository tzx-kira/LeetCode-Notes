package Unclassified;

import java.util.HashMap;
import java.util.Map;

/*
题目描述：
3003.执行操作后的最大分割数量
给你一个下标从 0 开始的字符串 s 和一个整数 k。
你需要执行以下分割操作，直到字符串 s 变为 空：
选择 s 的最长 前缀，该前缀最多包含 k 个 不同 字符。
删除 这个前缀，并将分割数量加一。如果有剩余字符，它们在 s 中保持原来的顺序。
执行操作之 前 ，你可以将 s 中 至多一处 下标的对应字符更改为另一个小写英文字母。
在最优选择情形下改变至多一处下标对应字符后，用整数表示并返回操作结束时得到的 最大 分割数量。

提示：
1 <= s.length <= 10^4
s 只包含小写英文字母。
1 <= k <= 26

*/
/* 收藏 */
public class Solution_3003 {
    /*
     * 解题思路：
     * 枚举
     */
    public int maxPartitionsAfterOperations(String s, int k) {
        Map<Long, Integer> memo = new HashMap<>();
        return dfs(0, 0, 0, memo, s.toCharArray(), k);
    }

    private int dfs(int i, int mask, int changed, Map<Long, Integer> memo, char[] s, int k) {
        if (i == s.length) {
            return 1;
        }

        // 把参数压缩到一个 long 中，方便作为哈希表的 key
        long args = (long) i << 32 | mask << 1 | changed;
        if (memo.containsKey(args)) { // 之前计算过
            return memo.get(args);
        }

        int res;
        // 不改 s[i]
        int bit = 1 << (s[i] - 'a');
        int newMask = mask | bit;
        // 集合大小超过了k
        if (Integer.bitCount(newMask) > k) {
            // 分割出一个子串，这个子串的最后一个字母在 i-1
            // s[i] 作为下一段的第一个字母，也就是 bit 作为下一段的 mask 的初始值
            res = dfs(i + 1, bit, changed, memo, s, k) + 1;
        } else { // 不分割，s[i]保留这一段
            res = dfs(i + 1, newMask, changed, memo, s, k);
        }

        if (changed == 0) {
            // 枚举把 s[i] 改成 第j个字母
            for (int j = 0; j < 26; j++) {
                newMask = mask | (1 << j);
                if (Integer.bitCount(newMask) > k) {
                    // 分割出一个子串，这个子串的最后一个字母在 i-1
                    // j 作为下一段的第一个字母，也就是 1<<j 作为下一段的 mask 的初始值
                    res = Math.max(res, dfs(i + 1, 1 << j, 1, memo, s, k) + 1);
                } else { // 不分割
                    res = Math.max(res, dfs(i + 1, newMask, 1, memo, s, k));
                }
            }
        }

        memo.put(args, res); // 记忆化
        return res;
    }


}
