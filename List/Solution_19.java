package List;
/*
19.删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
提示：
链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
进阶：你能尝试使用一趟扫描实现吗？
 */
public class Solution_19 {
    class ListNode{
        int val;
        ListNode next, prev;
        ListNode(int val){
            this.val = val;
        }
    }
    /*
     * 思路：使用双指针，快指针先走n步，然后快慢指针一起走，直到快指针到达链表末尾，此时慢指针指向的节点就是倒数第n个节点
     * 复杂度：时间O(sz)，空间O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0); // 设置一个虚拟头结点，防止删除头结点时出现空指针异常
        dummyNode.next = head;
        //快慢指针指向虚拟头节点
        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;
        //快指针先走n+1步，包括了虚拟头节点
        for (int i = 0; i <= n; i++) {
            fastIndex = fastIndex.next;
        }
        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }
        //删除倒数第n个节点
        if (slowIndex.next != null) {
            slowIndex.next = slowIndex.next.next;
        }
        return dummyNode.next;
    }
}