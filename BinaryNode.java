/*
 * file: BinaryNode.java
 * author: D. Choi
 * class: CS 241- Data Structures and Algorithms II
 * 
 * assignment: program 1
 * date last modified: 10/15/2017
 * 
 * purpose: This program represents a generic node in a binary tree.
 */
public class BinaryNode<T> {

	private T data;

	private BinaryNode<T> leftChild;

	private BinaryNode<T> rightChild;

	public BinaryNode() {
		this(null);
	}

	public BinaryNode(T content) {
		this(content, null, null);
	}

	public BinaryNode(T content, BinaryNode<T> left, BinaryNode<T> right) {
		data = content;
		leftChild = left;
		rightChild = right;
	}// end constructors

	/**
	 * method:setData
	 * purpose: this method replaces the current data held in
	 * this node with the new data passed in as an argument
	 */
	public void setData(T content) {
		data = content;
	}

	/**
	 * method: setLeftChild, setRightChild
	 * purpose: this method replaces the
	 * current left/right child of this node with the new node passed in as an
	 * argument
	 */
	public void setLeftChild(BinaryNode<T> left) {
		leftChild = left;
	}

	public void setRightChild(BinaryNode<T> right) {
		rightChild = right;
	}

	/**
	 * method: getData
	 * purpose: this method returns the data currently held in
	 * this node to the user
	 */
	public T getData() {
		return data;
	}

	/**
	 * method: getLeftChild, getRightChild
	 * purpose: this method returns the
	 * current left/right child to this node to the user
	 */
	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}

	public BinaryNode<T> getRightChild() {
		return rightChild;
	}

	/**
	 * method: hasLeftChild, hasRightChild
	 * purpose: this method checks to see if
	 * this node currently has a left/right child and returns whether if does or
	 * not
	 */
	public boolean hasLeftChild() {
		return leftChild != null;
	}

	public boolean hasRightChild() {
		return rightChild != null;
	}

	/**
	 * method: isLeaf
	 * purpose: this method checks to see if this node is a leaf
	 * in the tree, or, in other words, has no children
	 */
	public boolean isLeaf() {
		return hasLeftChild() && hasRightChild();
	}

	/**
	 * method: getNumberOfNodes
	 * purpose: this method counts the number of nodes
	 * in the subtree that this node is the root of and returns that number to
	 * the user
	 */
	public int getNumberOfNodes() {
		int leftNodes = 0;
		int rightNodes = 0;
		if (leftChild != null) {
			leftNodes = leftChild.getNumberOfNodes();
		}
		if (rightChild != null) {
			rightNodes = rightChild.getNumberOfNodes();
		}
		return 1 + leftNodes + rightNodes;
	}

	/**
	 * method: getHeight
	 * purpose: this method calls a private helper method to
	 * calculate the height of the subtree this node is the root of
	 */
	public int getHeight() {
		return getHeight(this);
	}

	/**
	 * method: getHeight
	 * purpose: this method is the private helper method that
	 * calculates the height of the subtree this node is the root of
	 */
	private int getHeight(BinaryNode<T> node) {
		int height = 0;
		if (node != null) {
			height = 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
		}
		return height;
	}

	/**
	 * method: copy
	 * purpose: this method creates a copy of this node, with the
	 * current data and two children, and returns the copy to the user
	 */
	public BinaryNode<T> copy() {
		BinaryNode<T> newNode = new BinaryNode<T>(data);
		if (leftChild != null) {
			newNode.setLeftChild(leftChild);
		}
		if (rightChild != null) {
			newNode.setRightChild(rightChild);
		}
		return newNode;
	}

}
