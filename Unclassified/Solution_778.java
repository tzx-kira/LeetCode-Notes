package Unclassified;
/*
778.水位上升的泳池中游泳
在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。
当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
提示:
n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n2
grid[i][j] 中每个值 均无重复
 */
public class Solution_778{
    /*
    解题思路：
    题目中的重要信息：可以 瞬间移动 无限距离，游动不耗时；水位要到达方格中的数字才能游过去那个格子
    方法一：二分查找 + 遍历
    单调性：
    如果等待的时间 t 越少，网格上可以游泳的部分就越少，存在从左上角到右下角的一条路径的可能性越小；
    如果等待的时间 t 越多，网格上可以游泳的部分就越多，存在从左上角到右下角的一条路径的可能性越大。
    因此可以使用二分查找定位到最短等待时间。具体来说：在区间 [0, N * N - 1] 里猜一个整数，针对这个整数从起点（左上角）开始做一次深度优先遍历或者广度优先遍历。
    当小于等于该数值时，如果存在一条从左上角到右下角的路径，说明答案可能是这个数值，也可能更小；
    当小于等于该数值时，如果不存在一条从左上角到右下角的路径，说明答案一定比这个数值更大。
    按照这种方式不断缩小搜索的区间，最终找到最少等待时间。


    方法二：并查集
     1.将二维坐标转换为一维坐标，方便并查集操作
     2.将所有格子按高度从低到高排序
     3.从高度最低的格子开始，逐渐将格子加入并查集，并将相邻且高度不超过当前高度的格子合并
     4.每次合并后，检查左上角和右下角是否连通，若连通则返回当前高度
    时间复杂度O(n^2log(n^2))，空间复杂度O(n^2)
     */
    private int n;
    public static final int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    // 使用深度优先遍历得到从 (x, y) 开始向四个方向的所有小于等于 threshold 且与 (x, y) 连通的结点
    private boolean dfs(int[][] grid, int x, int y, int mid, boolean[][] visited) {
        if (x == n - 1 && y == n - 1) {
            return true;
        }
        visited[x][y] = true;
        for (int[] dir : directions) {
            int newX = x + dir[0], newY = y + dir[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < n && !visited[newX][newY] && grid[newX][newY] <= mid) {
                if (dfs(grid, newX, newY, mid, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    public int swimInWater(int[][] grid) {
        /* 二分查找+dfs */
        this.n = grid.length;
        int left = grid[0][0], right = n * n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean[][] visited = new boolean[n][n];
            if (dfs(grid, 0, 0, mid, visited)) {
                // // mid 可以，尝试 mid 小一点,下一轮搜索的区间 [left, mid]
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}