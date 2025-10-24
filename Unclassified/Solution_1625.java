package Unclassified;
/*
1625.执行操作后字典序最小的字符串
给你一个字符串 s 以及两个整数 a 和 b 。其中，字符串 s 的长度为偶数，且仅由数字 0 到 9 组成。
你可以在 s 上按任意顺序多次执行下面两个操作之一：
累加：将  a 加到 s 中所有下标为奇数的元素上（下标从 0 开始）。数字一旦超过 9 就会变成 0，如此循环往复。例如，s = "3456" 且 a = 5，则执行此操作后 s 变成 "3951"。
轮转：将 s 向右轮转 b 位。例如，s = "3456" 且 b = 1，则执行此操作后 s 变成 "6345"。
请你返回在 s 上执行上述操作任意次后可以得到的 字典序最小 的字符串。
如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符出现在字母表中的时间早于 b 中的对应字符。例如，"0158” 字典序比 "0190" 小，因为不同的第一个位置是在第三个字符，显然 '5' 出现在 '9' 之前。

提示：
2 <= s.length <= 100
s.length 是偶数
s 仅由数字 0 到 9 组成
1 <= a <= 9
1 <= b <= s.length - 1

 */
public class Solution_1625 {
    /*
     * 解题思路：
     * 模拟 + 枚举
     * 1.枚举做轮转的次数，然后令 t 为 s 轮转后的字符串。由于轮转最终会产生循环，且至多轮转 n 次（n 为 s 的长度），因此我们用一个数组 vis 来记录每个位置是否被轮转过。如果遇到之前轮转过的位置，则枚举结束。
     * 2.对于每个 t，枚举对 t 的奇数位做累加操作的次数 j，再枚举对 t 的偶数位做累加操作的次数 k。这里因为元素值范围是 [0,9]，因此我们做累加操作的次数上限是 9，再多势必会产生循环。特殊的，如果 b 是偶数，则 k 的上限是 0，否则是 9。
    */
    public String findLexSmallestString(String s, int a, int b) {
        int n=s.length();
        boolean[] visited=new boolean[n]; //记录每个轮转起点是否访问过
        String res=s;
        s = s + s; //字符串拼接，方便轮转操作
        for(int i=0;!visited[i];i=(i+b)%n){ // 轮转操作，每次轮转 b 位
            visited[i]=true;
            for(int j=0;j<10;j++){
                int kLimit = b % 2 == 0 ? 0 : 9;
                for (int k = 0; k <= kLimit; k++) {
                    // 每次进行累加之前，重新截取 t
                    char[] t = s.substring(i, i + n).toCharArray(); //截取轮转后的字符串
                    for (int p = 1; p < n; p += 2) { //对奇数位进行累加
                        t[p] = (char) ('0' + (t[p] - '0' + j * a) % 10);
                    }
                    for (int p = 0; p < n; p += 2) { //对偶数位进行累加
                        t[p] = (char) ('0' + (t[p] - '0' + k * a) % 10);
                    }
                    String tStr = new String(t);
                    if (tStr.compareTo(res) < 0) { //比较字典序
                        res = tStr;
                    }
                }

            }
        }
        return res;
    }
}
