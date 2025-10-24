package List;

import List.Solution_19.ListNode;

/*
160.相交链表
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
题目数据 保证 整个链式结构中不存在环。
注意，函数返回结果后，链表必须 保持其原始结构 。
提示：
listA 中节点数目为 m
listB 中节点数目为 n
1 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
如果 listA 和 listB 没有交点，intersectVal 为 0
如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
 */
public class Solution_160 {
    /*
    解题思路：
    双指针
    求出两个链表的长度，并求出两个链表长度的差值，然后让curA移动到，和curB 末尾对齐的位置
    此时我们就可以比较curA和curB是否相同，如果不相同，同时向后移动curA和curB，如果遇到curA == curB，则找到交点。
    否则循环退出返回空指针。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cur1=headA,cur2=headB;
        int lenA = 0, lenB = 0;
        while(cur1!=null){ // 计算链表A的长度
            lenA++;
            cur1=cur1.next;
        }
        while(cur2!=null){ // 计算链表B的长度
            lenB++;
            cur2=cur2.next;
        }
        cur1 = headA;
        cur2 = headB;
        // 让curA为最长链表的头，lenA为其长度
        if(lenB > lenA){ 
            //1. swap (lenA, lenB);
            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
            //2. swap (curA, curB);
            ListNode tmpNode = cur1;
            cur1 = cur2;
            cur2 = tmpNode;
        }
        // 求长度差
        int gap = lenA - lenB;
        // 让curA和curB在同一起点上（末尾位置对齐）
        while (gap-- > 0) {
            cur1 = cur1.next;
        }
        // 遍历curA 和 curB，遇到相同则直接返回
        while(cur1!=null){
            if(cur1==cur2) return cur1;
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return null;
    }
}
