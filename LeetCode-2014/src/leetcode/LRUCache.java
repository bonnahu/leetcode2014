package leetcode;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Mar. 2, 2014
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
public class LRUCache {
	private LinkedHashMap<Integer, Integer> linkedHashMap;
	private int maxCapacity;
	private int curCapacity;
	
	public LRUCache(int capacity) {
		linkedHashMap = new LinkedHashMap<Integer, Integer>();
		this.maxCapacity = capacity;
		this.curCapacity = 0;
    }
    
    public int get(int key) {
    	if (linkedHashMap.get(key)==null)
    		return -1;
    	else{
    		// if the key already exists in the cache,
    		// save the value to retVal
    		int retVal = linkedHashMap.get(key);
    		// remove the item from linkedhashmap and
    		// add it to the end of linkedhashmap again
    		linkedHashMap.remove(key);
    		linkedHashMap.put(key, retVal);
    		return retVal;
    	}
    	
    }
    
    public void set(int key, int value) {
    	// if the key already exists in the cache
    	if (linkedHashMap.get(key)!= null){
    		// remove the item from linkedhashmap and
    		// add key with new value to the end of 
    		// linkedhashmap again
    		linkedHashMap.remove(key);
    		linkedHashMap.put(key, value);
    	}else{
	    	// if hit the maximum capacity
	        if(curCapacity == maxCapacity){  
	        	// get the first entry of the linkedhashmap
	        	Entry<Integer, Integer> firstEntry = linkedHashMap.entrySet().iterator().next();
	        	// remove the first entry
	        	linkedHashMap.remove(firstEntry.getKey());
	        	curCapacity--;
	        }
	        linkedHashMap.put(key, value);
	        curCapacity++;
    	}
    }
}
