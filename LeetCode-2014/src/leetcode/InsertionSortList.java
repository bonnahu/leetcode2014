package leetcode;
/**
 * Date: April 2nd, 2014
 *  
 * Difficulty: medium(2)
 * 
 * Key Point: Use a dummyHead as the head for the 
 * new returning sorted list
 * 
 * Sort a linked list using insertion sort.
 * @author Lei
 *
 */
public class InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		if(head==null || head.next==null)
			return head;
		// Use a dummyHead as the head node for the new sorted
		// list
		ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
		// indexing node for the original list
		ListNode curNode = head;
		// indexing node for the new sorted list
		ListNode newCurNode;
		ListNode preNode;
		ListNode nextNode;
		// scan the original list using the curNode 
		// indexing node
		while(curNode!=null){
			// save the next node
			nextNode= curNode.next;
			// for each curNdoe, we need to scan the already
			// sorted list to find the insert position 
			preNode=dummyHead;
			newCurNode = dummyHead.next;
			// scan the sorted list until hit the end or find the
			// correct position
			while(newCurNode!=null && newCurNode.val<curNode.val){
				preNode=newCurNode;
				newCurNode=newCurNode.next;
			}
			// insert the curNode between preNode and newCurNode
			preNode.next = curNode;
			curNode.next = newCurNode;
			curNode = nextNode;
		}
		return dummyHead.next;
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
		printList(new InsertionSortList().insertionSortList(n1));
	}
}
