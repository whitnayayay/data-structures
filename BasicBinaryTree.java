package ds;

public class BasicBinaryTree<X extends Comparable<X>> {
	private Node root;
	private int size;
	
	//constructor for BasicBinaryTree
	public BasicBinaryTree() {
		this.root = null;
	}
	
	public int size() {
		return size;
	}
	
	public void add(X item) {
		Node node = new Node(item);
		
		//if this is an empty tree, set it as root
		if(root == null) {
			this.root = node;
			System.out.println("Set root: " + node.getItem());
			this.size++;
		}
		//otherwise we need to insert the item into the tree using the insert algorithem
		else {
			insert(this.root, node);
		}
	}
	
	public boolean contains(X item) {
		Node currentNode = getNode(item);
		if (currentNode == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean delete(X item) {
		boolean deleted = false;
		
		if (this.root == null) {
			return false;
		}
		
		//find the node to delete
		Node currentNode = getNode(item);
		
		if(currentNode != null) {
			//if node to delete doesn't have any children, just delete it
			if(currentNode.getLeft() == null && currentNode.getRight() == null) {
				unlink(currentNode, null);
				deleted = true;
			}
			//if the node to delete only has a right child
			else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
				unlink(currentNode, currentNode.getRight());
				deleted = true;	
			}
			//if the node to delete only has a left child.
			else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
				unlink(currentNode, currentNode.getLeft());
				deleted = true;
			}
			//the node to delete has both children, do a node swap to delete
			else {
				//can swap out the node with the right most leaf node on the left side
				Node child = currentNode.getLeft();
				while(child.getRight() != null && child.getLeft() != null) {
					child = child.getRight();
				}
				
				//we have the right most leaf node. we can replace the curr node with this node
				child.getParent().setRight(null); // removes the leaf node from its current position
				
				child.setLeft(currentNode.getLeft());
				child.setRight(currentNode.getRight());
				
				unlink(currentNode, child);
				deleted = true;
			}
		}
		
		if (deleted) {
			this.size--;
		}
		
		return deleted;
	}
	
	private void unlink(Node currentNode, Node newNode) {
		//if this is root node, replace that differently
		if (currentNode == this.root) {
			newNode.setLeft(currentNode.getLeft());
			newNode.setRight(currentNode.getRight());
			this.root = newNode;
		}
		//if curr node is on right side of parent, we reset that new parent node to the new node.
		else if (currentNode.getParent().getRight() == currentNode) { 
			currentNode.getParent().setRight(newNode);
		}
		//curr node is on the left side of parent
		else {
			currentNode.getParent().setLeft(newNode);
		}
	}
	
	private Node getNode(X item) {
		Node currentNode = this.root; 
		
		while(currentNode != null) {
			int val = item.compareTo(currentNode.getItem()); //comparable interface. val will be < 0, 0, or > 0
			
			//see if the node is a match
			if(val == 0) {
				return currentNode;
			}
			//if the val is < 0, we go to left side of tree
			else if (val < 0) {
				currentNode = currentNode.getLeft();
			}
			//otherwise we go to the right
			else {
				currentNode = currentNode.getRight();
			}
		}
		return null;
	}
	
	//helper method for recursion
	//compare nodes as u add them
	private void insert(Node parent, Node child) {
		//if child is < parent, then child goes on left
		if(child.getItem().compareTo(parent.getItem()) < 0) { //java's comparable method compareTo. < 0 means number is less 
			if(parent.getLeft() == null) {
				parent.setLeft(child);;
				child.setParent(parent);;
				this.size++;
			}
			//otherwise we need to call insert again and test for left or right (recursion)
			else {
				insert(parent.getLeft(), child);
			}
		}
		//if the child is greater than the parent, it goes on the right
		else if(child.getItem().compareTo(parent.getItem()) > 0) { //comparable object. > 0 means it is greater
			//if the right node is null, we've found our spot
			if(parent.getRight() == null) {
				parent.setRight(child);
				child.setParent(parent);
				this.size++;
			}
			//otherwise we need to call insert again and test for left or right (recursive)
			else {
				insert(parent.getRight(), child);
			}
		}
		
		//if parent and child are equal, we dont do anything
		//data in binary tree is assumed to be unique and value already exists in the tree.
		
	}
	
	//current Node 
	private class Node {
		private Node left;
		private Node right;
		private Node parent;
		private X item;
		
		//constructor. initializes everything to null
		public Node(X item) {
			this.item = item;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		//getters and setters for all nodes in our class
		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public X getItem() {
			return item;
		}

		public void setItem(X item) {
			this.item = item;
		}
		
		
	}
	
	
	
}
