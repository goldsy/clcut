/**
 * This class is a binary search tree for cloth rectangles because using
 * an array just sucks.
 * 
 * @author Jeff
 *
 */
public class RectangleBST {
    /**
     * This inner class supports the tree.
     * 
     * @author Jeff
     *
     */
    public class Node {
    	int key = -1;
    	ClothRectangle data = null;
        
    	Node left = null;
    	Node right = null;
        
        
    	/**
    	 * Class ctor.
         * 
    	 * @param _source
    	 * @param _left
    	 * @param _right
    	 */
        public Node(ClothRectangle _source, Node _left, Node _right) {
            data = _source;
            key = _source.getKey();
            left = _left;
            right = _right;
        }
        
        
        /**
         * Gets the key for this node.
         * 
         * @return
         * This method returns the key for this node.
         */
    	public int getKey() {
    		return key;
    	}
        
    	
        /**
         * Gets the data for this node.
         * 
         * @return
         * This method returns the data stored in this node.
         */
    	public ClothRectangle getData() {
    		return data;
    	}
        
    	
        /**
         * Gets the left child node.
         * 
         * @return
         * This method returns the left child node, or null if one does not
         * exist.
         */
    	public Node getLeft() {
    		return left;
    	}
        
        
        /**
         * Sets the left child for this node.
         * 
         * @param sourceNode
         * The node to set as the left child.
         */
    	public void setLeft(Node sourceNode) {
    		left = sourceNode;
    	}
    	
        /**
         * Gets the right child node.
         * 
         * @return
         * This method returns the right child node, or null if one does not
         * exist.
         */
    	public Node getRight() {
    		return right;
    	}
        
        
        /**
         * Sets the right child for this node.
         * 
         * @param sourceNode
         * The node to set as the right child.
         */
    	public void setRight(Node sourceNode) {
    		right = sourceNode;
    	}
    }
	
    Node root = null;
	
    
    public RectangleBST() {
    	
    }
    
    /**
     * Find method for the tree.  This is the public method that will simply
     * grab the root and call the private version of find using the node.
     * 
     * @param targetKey
     * The target key to look for.
     * 
     * @return
     * This method returns a cloth rectangle if a match is found and null
     * otherwise.
     */
    public ClothRectangle find(int targetKey) {
    	return find(root, targetKey);
    }
    
    
    /**
     * This method is the private version of find which uses the node and
     * target key to check the current node and subsequently its children
     * for a match to the target key.
     * 
     * @param currNode
     * The node to check.
     * 
     * @param targetKey
     * The key to look for.
     * 
     * @return
     * This method returns a cloth rectangle if a match is found and null
     * otherwise.
     */
    private ClothRectangle find(Node currNode, int targetKey) {
    	if (currNode == null) {
    		// DEBUG
    		//System.out.println("Node [" + targetKey + "] not found.");
    		return null;
    	}
        
    	if (currNode.getKey() == targetKey) {
    		return currNode.getData();
    	}
    	else if (targetKey < currNode.getKey()) {
            // Node must be in the left tree.
    		return find (currNode.getLeft(), targetKey);
    	}
    	else {
    		// Node must be in the right tree.
            return find (currNode.getRight(), targetKey);
    	}
    }
    
    
    /**
     * Inserts the source rectangle into the tree.
     * 
     * @param sourceRectangle
     * The source rectangle to insert into the tree.
     */
    public static int size = 0;
    public void insert(ClothRectangle sourceRectangle) {
        // If the tree is empty insert at the root.
    	if (root == null) {
    		// DEBUG
            ++size;
    		System.out.println("Size [" + size + "]");
    		root = new Node(sourceRectangle, null, null);
    		return;
    	}
        
    	Node currNode = root;
        
    	while (currNode != null) {
    		// Exclude this rectangle if it already exists, although we should
    		// have already checked this before trying to insert.
    		if (sourceRectangle.getKey() == currNode.getKey()) {
    			return;
    		}
    		else if (sourceRectangle.getKey() < currNode.getKey()) {
    			if (currNode.getLeft() == null) {
    				// Insert into left side.
    				// DEBUG
    				++size;
    				System.out.println("Size [" + size + "][" + sourceRectangle.getKey() + "] Left");
    				currNode.setLeft(new Node(sourceRectangle, null, null));
                    
    				return;
    			} 
    			else {
                    // Set the current node to the left child and loop again.
    				currNode = currNode.getLeft();
    			}
    		} 
    		else {
    			if (currNode.getRight() == null) {
    				// Insert into right side.
    				// DEBUG
    				++size;
    				System.out.println("Size [" + size + "][" + sourceRectangle.getKey() + "] Right");
    				currNode.setRight(new Node(sourceRectangle, null, null));
                    
    				return;
    			} 
    			else {
                    // Set the current not to the right child and loop again.
    				currNode = currNode.getRight();
    			}
    		}
    	}
    }
}
