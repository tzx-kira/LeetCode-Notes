package Array;

import java.util.Scanner;

/*
卡玛网44
开发商购买土地
在一个城市区域内，被划分成了n * m个连续的区块，每个区块都拥有不同的权值，代表着其土地价值。目前，有两家开发公司，A 公司和 B 公司，希望购买这个城市区域的土地。
现在，需要将这个城市区域的所有区块分配给 A 公司和 B 公司。
然而，由于城市规划的限制，只允许将区域按横向或纵向划分成两个子区域，而且每个子区域都必须包含一个或多个区块。
为了确保公平竞争，你需要找到一种分配方式，使得 A 公司和 B 公司各自的子区域内的土地总价值之差最小。
注意：区块不可再分。
 */
public class Solution_kama44 {
    /*
     * 解题思路：
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int sum = 0;
        int[][] vec = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                vec[i][j] = scanner.nextInt();
                sum += vec[i][j];
            }
        }

        // 统计横向
        int[] horizontal = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                horizontal[i] += vec[i][j];
            }
        }

        // 统计纵向
        int[] vertical = new int[m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                vertical[j] += vec[i][j];
            }
        }

        int result = Integer.MAX_VALUE;
        int horizontalCut = 0;
        for (int i = 0; i < n; i++) {
            horizontalCut += horizontal[i]; // 前i行的和
            result = Math.min(result, Math.abs((sum - horizontalCut) - horizontalCut));
            // 更新result。其中，horizontalCut表示前i行的和，sum - horizontalCut表示剩下的和，作差、取绝对值，得到题目需要的“A和B各自的子区域内的土地总价值之差”。下同。
        }

        int verticalCut = 0;
        for (int j = 0; j < m; j++) {
            verticalCut += vertical[j]; // 前j列的和
            result = Math.min(result, Math.abs((sum - verticalCut) - verticalCut));
        }

        System.out.println(result);
        scanner.close();
    }
}
