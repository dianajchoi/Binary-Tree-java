/**
 * file: TreeInterface.java
 * author: D. Choi
 * class: CS 241 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 10/15/2017
 * 
 * purpose: This program is a general template for any general tree.
 * 
 */
public interface TreeInterface<T> {

	/**
	 * method: getRootData
	 * purpose: this method returns the data held in the root node of this tree
	 */
	public T getRootData();
	
	/**
	 * method: getHeight
	 * purpose: this method returns how many levels away the furthest leaf is from the root node
	 */
	public int getHeight();
	
	/**
	 * method: getNumberOfNodes
	 * purpose: this method counts and returns the number of nodes in this tree
	 */
	public int getNumberOfNodes();
	
	/**
	 * method: isEmpty
	 * purpose: this method returns whether or not this tree is empty
	 */
	public boolean isEmpty();
	
	/**
	 * method: clear
	 * purpose: this method removes all nodes in this tree
	 */
	public void clear();
}
