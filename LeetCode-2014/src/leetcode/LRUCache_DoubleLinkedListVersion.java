package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Dec. 12, 2014
 * 
 * Design and implement a data structure for Least Recently 
 * Used (LRU) cache. It should support the following 
 * operations: get and set.
 * get(key) - Get the value (will always be positive) of 
 * the key if the key exists in the cache, otherwise return -1.
 * 
 * set(key, value) - Set or insert the value if the key 
 * is not already present. When the cache reached its capacity, 
 * it should invalidate the least recently used item 
 * before inserting a new item.
 * @author Lei
 *
 */
class DoubleListNode{
	public DoubleListNode previous;
	public DoubleListNode next;
	// this key and val is used to store the <key,value> of the hashmap item
	public int key;
	public int val;
	//Constructor
	public DoubleListNode(int _key, int _val){
		previous = null;
		next = null;
		val = _val;
		key = _key;
	}
}
/**
 * Main idea of the solution:
 * My first version was to use a DoublyLindedlist to 
 * record the order of the node, i.e., 
 * Map<Integer, Integer> hsMap +
 * DoubleLinkedList<Integer> dList
 * The problem of this solution is the remove operation
 * needs O(N), which will get a Time Limit Exceeded error
 * for the large test case. So I got the hint from others' 
 * solution, put the DoubleListNode as the value of the
 * hashMap:
 * hsMap= new HashMap<Integer, DoubleListNode>();
 * This can reduce the remove operation to O(1).
 * @author Lei
 *
 */
public class LRUCache_DoubleLinkedListVersion {
	private int capacity;
	private int curSize;
	private Map<Integer, DoubleListNode> hsMap;
	// here we use two dummy nodes
	private DoubleListNode head;
	private DoubleListNode tail;
	public LRUCache_DoubleLinkedListVersion(int capacity) {
		hsMap= new HashMap<Integer, DoubleListNode>();
		// use two dummy nodes, head and tail
		head = new DoubleListNode(Integer.MIN_VALUE,Integer.MIN_VALUE);
		tail = new DoubleListNode(Integer.MIN_VALUE,Integer.MIN_VALUE);
		head.next = tail;
		tail.previous = head;
		curSize = 0;
		this.capacity = capacity;
    }
    
    public int get(int key) {
    	if(!hsMap.containsKey(key)){
    		return -1;
    	}else{//the contains the key
    		DoubleListNode curNode =hsMap.get(key);
    		// remove curNode from the double list,
    		// here just need O(1)
    		removeFromDList(curNode);
    		// put curNode before the tail node, since 
    		// it has been visited
    		putToTail(curNode);
    		return curNode.val;
    	}
    }
    
    public void set(int key, int value) {
    	// create an instance of DoubleListNode
    	// with the key and value
    	DoubleListNode newNode = new DoubleListNode(key, value);
    	if(hsMap.containsKey(key)){// contains the key
    		DoubleListNode curNode =hsMap.get(key);
    		removeFromDList(curNode);
    	}else {
    		if(curSize<capacity){
        		curSize++;
        	}else{
        		// if it is full, then 
        		// 1. remove the least recent visited node's key from hashmap
        		// 2. remove the least recent visited node from the list
        		hsMap.remove(head.next.key);
        		// remove the first node
        		head.next.next.previous = head;// order is very important
        		head.next = head.next.next;   		
        	}
    	}
    	// insert the key with its newNode to hashmap
		hsMap.put(key, newNode);
		// put newNode to the tail
		putToTail(newNode);
    		
    }
    private void putToTail(DoubleListNode curNode){
    	tail.previous.next = curNode;
		curNode.previous = tail.previous;
		curNode.next = tail;
		tail.previous = curNode;
    }
    /**
     * remve the curNode from a DoublyLinkedLsit
     * @param curNode
     */
    private void removeFromDList(DoubleListNode curNode){
		curNode.previous.next = curNode.next;
		curNode.next.previous = curNode.previous;
    }
    
    public static void main(String[] args){
    	LRUCache_DoubleLinkedListVersion lruObj = new LRUCache_DoubleLinkedListVersion(1);
    	lruObj.set(2,1);
    	System.out.println(lruObj.get(2));
    	lruObj.set(3,2);
    	System.out.println(lruObj.get(2));
    	System.out.println(lruObj.get(3));
    }
}
