/**
 * file: SearchTreeInterface.java
 * author: D. Choi
 * class: CS 241 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 10/15/2017
 * 
 * purpose: This program is a general template for a search tree, which extends a general tree.
 * 
 */
import java.util.Iterator;

public interface SearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T> {

	/**
	 * method: contains
	 * purpose: This method checks to see if a data entry
	 * exists within this tree
	 */
	public boolean contains(T data);

	/**
	 * method: getEntry
	 * purpose: This method returns the desired data entry if
	 * it exists within this tree
	 */
	public T getEntry(T data);

	/**
	 * method: addEntry
	 * purpose: This method adds a new entry into this tree
	 * with the value of data to be held in the new entry
	 */
	public T addEntry(T data);

	/**
	 * method: remove
	 * purpose: This method removes an entry currently holding data.
	 * Since this program does not allow duplicates, the only possible
	 * candidate for removal is the one entry in the tree holding this
	 * particular target data
	 */
	public T remove(T data);

	/**
	 * method: getInorderTraversal
	 * purpose: This method allows the user to use the Iterator to
	 * iterate over the tree using the inorder traversal technique
	 */
	public Iterator<T> getInorderTraversal();
}
