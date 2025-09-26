package Array;
/*
59.螺旋矩阵 II
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
提示：
1 <= n <= 20
 */
public class Solution_59 {
    /*
     * 解题思路：
     * 模拟
     * 定义四个变量，top、bottom、left、right，分别表示当前矩阵的上下左右边界
     * 初始时，top=0，bottom=n-1，left=0，right=n-1
     * 定义一个变量num，表示当前填入矩阵的数字，初始值为1
     * 当top<=bottom且left<=right时，循环执行以下操作：
     * 1. 从左到右遍历上边界top，将num依次填入matrix[top][i]，然后top++
     * 2. 从上到下遍历右边界right，将num依次填入matrix[i][right]，然后right--
     * 3. 如果top<=bottom，从右到左遍历下边界bottom，将num依次填入matrix[bottom][i]，然后bottom--
     * 4. 如果left<=right，从下到上遍历左边界left，将num依次填入matrix[i][left]，然后left++
     * 循环结束后，返回matrix
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix=new int[n][n];
        int top=0,bottom=n-1,left=0,right=n-1;
        int num=1;
        while(top<=bottom&&left<=right){
            // 遍历上边界
            for(int i=left;i<=right;i++){
                matrix[top][i]=num++;
            }
            top++;
            // 遍历右边界
            for(int i=top;i<=bottom;i++){
                matrix[i][right]=num++;
            }
            right--;
            if(top<=bottom){
                // 遍历下边界
                for(int i=right;i>=left;i--){
                    matrix[bottom][i]=num++;
                }
                bottom--;
            }
            if(left<=right){
                // 遍历左边界
                for(int i=bottom;i>=top;i--){
                    matrix[i][left]=num++;
                }
                left++;
            }
        }
        return matrix;
    }
}
