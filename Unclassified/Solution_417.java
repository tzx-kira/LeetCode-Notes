package Unclassified;

import java.util.ArrayList;
import java.util.List;

/*
417.太平洋大西洋水流问题
有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
提示：

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 10^5
 */
public class Solution_417 {
    /*
    思路：从太平洋和大西洋的边界出发，分别进行DFS或者BFS，反向搜索，每个节点是否能从太平洋和大西洋边界反到达，从而找出能流入太平洋和大西洋的所有节点，最后取交集
    复杂度：时间O(m*n)，空间O(m*n)
     */
    int[][]dx= {{1,0},{-1,0},{0,1},{0,-1}}; //四个方向，下上右左
    private void dfs(int[][] heights, boolean[][] visited, int x, int y){
        visited[x][y]=true; //标记为已访问
        // 四个方向搜索
        for(int[]d:dx){
            int nx=x+d[0];
            int ny=y+d[1];
            if(nx>=0 && nx<heights.length && ny>=0 && ny<heights[0].length
                    && !visited[nx][ny] && heights[nx][ny]>=heights[x][y]){ //下一个节点没有访问过且高度不小于当前节点
                dfs(heights,visited,nx,ny);
            }
        }
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>>ans=new ArrayList<>();
        boolean[][] v1=new boolean[m][n]; //能流入太平洋
        boolean[][] v2=new boolean[m][n]; //能流入大西洋
        //从太平洋边界出发
        for(int i=0;i<m;i++){
            dfs(heights,v1,i,0);
            dfs(heights,v2,i,n-1);
        }
        //从大西洋边界出发
        for(int j=0;j<n;j++){
            dfs(heights,v1,0,j);
            dfs(heights,v2,m-1,j);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(v1[i][j] && v2[i][j]){
                    List<Integer>list=new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
