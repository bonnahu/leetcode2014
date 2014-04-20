package leetcode;



/**
 * Sort a linked list in O(n log n) time using 
 * constant space complexity.
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 * @author Lei
 *
 */
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}
public class SortList {
	
	public  ListNode findMidNode(ListNode head){
		if(head==null || head.next==null)
			return head;
		ListNode twoStepCursor = head;
		ListNode oneStepCursor = head;
		while(twoStepCursor!=null&&twoStepCursor.next!=null&&twoStepCursor.next.next!=null){
			twoStepCursor = twoStepCursor.next.next;
			oneStepCursor = oneStepCursor.next;
		}
		return oneStepCursor;
	}
	
	
	private ListNode mergeTwoLists(ListNode head1, ListNode head2){
		if (head1==null)
			return head2;
		if (head2==null)
			return head1;
		// create a dummy head node
		ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
		ListNode curNode= dummyHead;
		ListNode curNode1 = head1;
		ListNode curNode2 = head2;
		while(curNode1!=null && curNode2!=null){
			if (curNode1.val < curNode2.val){
				curNode.next = curNode1;
				curNode1=curNode1.next;
			}else{
				curNode.next = curNode2;
				curNode2=curNode2.next;
			}
			curNode = curNode.next;
		}
		if(curNode1!=null)
			curNode.next = curNode1;
		if(curNode2!=null)
			curNode.next = curNode2;
		return dummyHead.next;	
	}
	
	public ListNode sortList(ListNode head) {
        if(head==null || head.next==null)
        	return head;
        else{
        	ListNode midNode = findMidNode(head);
        	ListNode secHead = midNode.next;
        	midNode.next = null;
        	ListNode retHead1 = sortList(head);
        	ListNode retHead2 = sortList(secHead);
        	return mergeTwoLists(retHead1, retHead2);
        }
    }
	
	private static void printList(ListNode head){
		ListNode curNode = head;
		while(curNode!=null){
			System.out.print(curNode.val + "->");
			curNode=curNode.next;
		}
	}
	public static void main(String[] args){
		
		ListNode n1= new ListNode(1);
		ListNode n2= new ListNode(2);
		ListNode n3= new ListNode(3);
		ListNode n4= new ListNode(4);
		ListNode n5= new ListNode(5);
		n1.next = n5;
		n5.next = n2;
		n2.next = n4;
		n4.next = n3;
		printList(new SortList().sortList(n1));
				
		
		
	}
}
