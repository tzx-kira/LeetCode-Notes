package Unclassified;
/*
2048.下一个更大的数值平衡数
如果整数  x 满足：对于每个数位 d ，这个数位 恰好 在 x 中出现 d 次。那么整数 x 就是一个 数值平衡数 。
给你一个整数 n ，请你返回 严格大于 n 的 最小数值平衡数 。
示例 1：
输入：n = 1
输出：22
解释：
22 是一个数值平衡数，因为：
- 数字 2 出现 2 次 
这也是严格大于 1 的最小数值平衡数。
提示：
0 <= n <= 10^6
 */
public class Solution_2048 {
    /*
     * 解题思路：
     * 枚举
     * 从 n+1 开始，逐个检查每个整数是否为数值平衡数。
     * 对于每个整数 x，将其转换为字符串以便逐位检查。
     * 使用一个数组 count 来记录每个数字出现的次数。
     * 遍历字符串的每一位数字 d，将 count[d] 增加 1。
     * 再次遍历字符串的每一位数字 d，检查 count[d] 是否等于 d。
     * 如果对于所有数字 d 都满足 count[d] == d，则 x 是一个数值平衡数，返回 x 作为结果。
     * 如果不满足条件，继续检查下一个整数。
     * 
     */
    public int nextBeautifulNumber(int n) {
        boolean found = false;

        while(found == false){
            n++;
            String s = Integer.toString(n);
            int[] count = new int[10];
            for(int i=0;i<s.length();i++){
                int digit = s.charAt(i) - '0';
                count[digit]++;
            }
            found = true;
            for(int i=0;i<s.length();i++){
                int digit = s.charAt(i) - '0';
                if(count[digit] != digit){
                    found = false;
                    break;
                }
            }
            if(found){
                return n;
            }
        }
        return n;
    }
}
