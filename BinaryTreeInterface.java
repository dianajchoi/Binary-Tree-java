/**
 * file: BinaryTreeInterface.java
 * author: D. Choi
 * class: CS 241 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 10/15/2017
 * 
 * purpose: This interface represents the general ADT Binary Tree and all its functions.
 * 
 */
import java.util.Iterator;

public interface BinaryTreeInterface<T> extends TreeInterface<T> {

	/**
	 * method: setTree
	 * purpose: This method creates the tree with a root node
	 * containing rootData
	 */
	public void setTree(T rootData);

	/**
	 * method: setTree
	 * purpose: This method creates the tree with a root node
	 * containing rootData, and with a left subtree leftTree and a right subtree
	 * rightTree
	 */
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree);

	/**
	 * method: inorderTraverse
	 * purpose: This method allows the user to traverse the tree using an inorder traversal technique
	 */
	public void inorderTraverse();

	/**
	 * method: preorderTraverse
	 * purpose: This method allows the user to traverse the tree using a preorder traversal technique
	 */
	public void preorderTraverse();

	/**
	 * method: postorderTraverse
	 * purpose: This method allows the user to traverse the tree using a postorder traversal technique
	 */
	public void postorderTraverse();

	/**
	 * method: getInorderTraversal
	 * purpose: This method allows the user to access an iterator that traverses the tree using the inorder technique
	 */
	public Iterator<T> getInorderTraversal();
}
