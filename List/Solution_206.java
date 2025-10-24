package List;

import java.util.List;

/*
206.反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
提示：

链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000
进阶：链表可以选用迭代或递归方式完成反转。
*/
public class Solution_206 {
    class ListNode{
        int val;
        ListNode next, prev;
        ListNode(int val){
            this.val = val;
        }
    }
    /*
    思路：迭代法，使用三个指针prev, curr, next分别指向前一个节点、当前节点和下一个节点
    复杂度：时间O(n)，空间O(1)
        递归法，反转后续节点，然后将当前节点的下一个节点指向当前节点
        复杂度：时间O(n)，空间O(n)
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode tmp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=tmp;
        }
        return pre;
    }
}
