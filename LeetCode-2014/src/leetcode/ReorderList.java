package leetcode;

/**
 * Given a singly linked list L: L0 -L1 -… -Ln-1 -Ln,
reorder it to: L0-Ln-L1-Ln-1-L2-Ln-2-…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class ReorderList {
    public static void reorderList(ListNode head) {
       reorderList_recur(head, 0, getLenth(head));
    }
   
    private static ListNode reorderList_recur(ListNode curNode, int index, int Len){
    	// base case
    	if (curNode==null||Len <3 )
              return curNode;
    	// base case: when the current node is the middle node,
    	// it means all the reorder operations are done, we need 
    	// to return the next node to the curNode, which is the 
    	// node needs to reorder the position
    	if(index==(Len-1)/2){
    		ListNode retNode;
    		// if the number of the list is odd,
    		// return the next node of the current
    		// node
    		if(Len % 2 != 0){
    			retNode = curNode.next;
		        curNode.next=null;
    		}else{
		        retNode = curNode.next.next;
		        curNode.next.next= null;
    		}
    		return retNode;
    	}else{// recursive case
    		// save the next node
    		ListNode nxtNode= curNode.next;
    		// recursively invoke the function
    		// The returning result should be the node to reorder
    		ListNode ret =  reorderList_recur(nxtNode, index+1, Len);
    		// save the next node of the ret node
            ListNode ret_nxt = ret.next;
            // reorder the ret node
            curNode.next = ret;
            ret.next = nxtNode;
            // return the next node to be reorder
            return ret_nxt;
        }
    }
   
    private static int getLenth(ListNode head){
       int len =0;
       ListNode curNode = head;
       while(curNode!=null){
              len++;
              curNode=curNode.next;
       }
       return len;
    }
   
    private static void printList(ListNode head){
       ListNode curNode = head;
       while(curNode!=null){
              System.out.print(curNode.val + "->");
              curNode=curNode.next;
       }
       System.out.println();
    }
   
    
    public static void main(String[] args){
       ListNode l1 = new ListNode(1);
       ListNode l2 = new ListNode(2);
       ListNode l3 = new ListNode(3);
       ListNode l4 = new ListNode(4);
       ListNode l5 = new ListNode(5);
       l1.next = l2;
       l2.next = l3;
       //l3.next = l4;
       //l4.next = l5;
       ReorderList.printList(l1);
       ReorderList.reorderList(l1);
       ReorderList.printList(l1);
    }
}