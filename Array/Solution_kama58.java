package Array;
import java.util.Scanner;
/*
卡玛网58
区间和
给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
 */

public class Solution_kama58 {
    /*
    解题思路：
    前缀和 
    定义一个前缀和数组 p，其中 p[i] 表示数组 Array 从下标 0 到 i 的元素和
    这样，区间 [a, b] 的元素和就可以表示为 p[b] - p[a-1]（当 a > 0 时），或者直接为 p[b]（当 a = 0 时）
    预处理数组 Array，计算出前缀和数组 p
    对于每个查询区间 [a, b]，使用前缀和数组 p 快速计算出区间和
    这样每次查询的时间复杂度为 O(1)，预处理的时间复杂度为 O(n)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] vec = new int[n];
        int[] p = new int[n];

        int presum = 0;
        for (int i = 0; i < n; i++) {
            vec[i] = scanner.nextInt();
            presum += vec[i];
            p[i] = presum;
        }

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int sum;
            if (a == 0) {
                sum = p[b];
            } else {
                sum = p[b] - p[a - 1];
            }
            System.out.println(sum);
        }

        scanner.close();
    }
}

