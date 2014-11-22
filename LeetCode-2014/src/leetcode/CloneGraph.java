package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Nov. 18, 2014
 * 
 * Clone an undirected graph. Each node in the graph 
 * contains a label and a list of its neighbors.
 * @author Lei
 *
 */

/**
 * Definition for undirected graph.
 * 
 */
class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	HashMap<UndirectedGraphNode, UndirectedGraphNode> cloned_nodes = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    	return cloneGraph_recur( node, cloned_nodes); 
    }
    /**
     * Recursion helper method
     * @param node
     * @param cloned_nodes
     * @return
     */
    private UndirectedGraphNode cloneGraph_recur(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> cloned_nodes) {
    	if (node == null)
        	return null;
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);;
    	// update the cloned_nodes
    	cloned_nodes.put(node, newNode);
    	// go through all the neighbor nodes
    	for(UndirectedGraphNode neighborNode : node.neighbors){
    		if (cloned_nodes.containsKey(neighborNode))
    		// if the current neighbor node has been cloned already
    			newNode.neighbors.add(cloned_nodes.get(neighborNode));
    		else{//recursively clone the neighborNode
    			UndirectedGraphNode cloned_node= cloneGraph_recur(neighborNode, cloned_nodes);
    			cloned_nodes.put(neighborNode, cloned_node);
    			newNode.neighbors.add(cloned_node);
    		}
    	}
        return newNode;
    }
}