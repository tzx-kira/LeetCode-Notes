package Unclassified;
/*
2011.执行操作后的变量值
存在一种仅支持 4 种操作和 1 个变量 X 的编程语言：
++X 和 X++ 使变量 X 的值 加 1
--X 和 X-- 使变量 X 的值 减 1
最初，X 的值是 0
给你一个字符串数组 operations ，这是由操作组成的一个列表，返回执行所有操作后， X 的 最终值 。
提示：
1 <= operations.length <= 100
operations[i] 将会是 "++X"、"X++"、"--X" 或 "X--"

 */
public class Solution_2011 {
    /*
     * 解题思路：
     * 遍历字符串数组，判断每个字符串的中间字符是+还是-，根据结果对res进行加1或减1操作
     */
    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for (String operation : operations) {
            if (operation.charAt(1) == '+') { // 判断中间字符，中间字符是不变的
                res++;
            } else {
                res--;
            }
        }
        return res;
    }
}
