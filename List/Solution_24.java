package List;

import java.util.List;

/*
24.两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
提示：
链表中节点的数目在范围 [0, 100] 内
0 <= Node.val <= 100
 */
public class Solution_24 {
    class ListNode{
        int val;
        ListNode next, prev;
        ListNode(int val){
            this.val = val;
        }
    }
    /*
     * 思路：使用一个虚拟头节点，遍历链表，每次交换两个节点，然后更新前一个节点pre
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0); // 设置一个虚拟头结点
        dummy.next = head;
        ListNode pre = dummy; // 记录前一个节点
        while(pre.next!=null && pre.next.next!=null){ //确保有两个能交换的节点
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;
            //交换
            pre.next = node2; //步骤1
            node1.next = node2.next; //步骤2
            node2.next = node1; //步骤3
            //更新pre
            pre = node1;

        }
        return dummy.next;
    }
}
