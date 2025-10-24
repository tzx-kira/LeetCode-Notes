package Unclassified;
/*
1518.换水问题
超市正在促销，你可以用 numExchange 个空水瓶从超市兑换一瓶水。最开始，你一共购入了 numBottles 瓶水。
如果喝掉了水瓶中的水，那么水瓶就会变成空的。
给你两个整数 numBottles 和 numExchange ，返回你 最多 可以喝到多少瓶水。
 */
public class Solution_1518 {
    /*
     * 思路：贪心，每次用尽可能多的空瓶兑换水
     * 复杂度：时间O(logn)，空间O(1)
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int res=numBottles;
        int empty=numBottles;
        while(empty>=numExchange){
            int newBottles=empty/numExchange;
            res+=newBottles;
            empty=empty%numExchange+newBottles;
        }
        return res;
    }
}
