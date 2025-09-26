package Unclassified;

import java.util.Arrays;

/*
611.有效三角形的个数
给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数
提示:
1 <= nums.length <= 1000
0 <= nums[i] <= 1000。
 */
public class Solution_611 {
    /*
     * 解题思路：
     * 三角形的三边关系:任意两边之和大于第三边,任意两边之差小于第三边。
     * 注意数组中可能会有重复元素
     * 排序+双指针
     * 排序后，固定最长边nums[k]，用双指针i和j分别指向最短边和中间边
     * 如果nums[i]+nums[j]>nums[k]，则说明以nums[k]为最长边的三角形中，所有以nums[i]为最短边，nums[j]为中间边的组合都是符合要求的
     * 即(i,j),(i,j-1),(i,j-2)...(i,i+1)都是符合要求的
     * 因为数组是排序的，所以只要nums[i]+nums[j]>nums[k]，那么nums[i]+nums[j-1]>nums[k]也是成立的
     * 反之，如果nums[i]+nums[j]<=nums[k]，则说明以nums[k]为最长边的三角形中，所有以nums[j]为中间边，nums[i]为最短边的组合都是不符合要求的
     * 即(i,j),(i+1,j),(i+2,j)...(j-1,j)都是不符合要求的
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        // 固定一条最长边 a 之后，剩下的两边都是在比 a 小的数里找的。只要剩余的两边满足 b + c > a，则一定满足 a + c > b 和 a + b > c。
        int res=0;
        for(int k=nums.length-1;k>=2;k--){ // 固定最长边
            int i=0,j=k-1; // 双指针分别指向最短边和中间边
            while(i<j){
                if(nums[i]+nums[j]>nums[k]){ // 找到一个符合要求的三元组
                    res+=j-i; // 以nums[k]为最长边的三角形中，所有以nums[i]为最短边，nums[j]为中间边的组合都是符合要求的
                    j--; // 移动中间边指针，寻找下一个符合要求的三元组
                }else{
                    i++; // 移动最短边指针，寻找下一个符合要求的三元组
                }
            }
            
        }
        return res;
    }
}
