/**
 * file: BinarySearchTree.java
 * author: D. Choi
 * class: CS 241 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 10/15/2017
 * 
 * purpose: This class is an implementation of the ADT Binary Search Tree using a Linked structure.
 * 
 */
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T> {

	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(T rootData) {
		super(rootData);
	}

	public void setTree(T rootData) {
		throw new UnsupportedOperationException();
	}

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	}

	public boolean contains(T data) {
		return getEntry(data) != null;
	}

	public T getEntry(T data) {
		return findEntry(getRootNode(), data);
	}

	/**
	 * method: findEntry
	 * purpose: This method is a private helper method that uses the node that
	 * is passed in as the root of a subtree to recursively traverse the tree
	 * and find the node holding the target data 
	 */
	private T findEntry(BinaryNode<T> rootNode, T data) {
		T result = null;
		if (rootNode != null) {
			if (data.compareTo(rootNode.getData()) == 0) {
				result = rootNode.getData();
			} else if (data.compareTo(rootNode.getData()) < 0) {
				result = findEntry(rootNode.getLeftChild(), data);
			} else {
				result = findEntry(rootNode.getRightChild(), data);
			}
		}
		return result;
	}

	public T addEntry(T data) {
		T result = null;
		if (getRootNode() == null) {
			setRootNode(new BinaryNode<T>(data));
		} else {
			result = add(getRootNode(), data);
		}
		return result;
	}

	/**
	 * method: add
	 * purpose: This method is a private helper method that uses the node that
	 * is passed in as the root of the subtree its is currently traversing to
	 * find the proper place to insert the new node that is to hold the new data
	 */
	private T add(BinaryNode<T> rootNode, T data) {
		assert rootNode != null;
		T result = null;
		int comp = data.compareTo(rootNode.getData());
		if (comp == 0) {
			result = rootNode.getData();
			rootNode.setData(data);
		} else if (comp < 0) {
			if (rootNode.hasLeftChild()) {
				add(rootNode.getLeftChild(), data);
			} else {
				rootNode.setLeftChild(new BinaryNode<T>(data));
			}
		} else {
			if (rootNode.hasRightChild()) {
				add(rootNode.getRightChild(), data);
			} else {
				rootNode.setRightChild(new BinaryNode<T>(data));
			}
		}
		return result;
	}

	public T remove(T data) {
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(getRootNode(), data, oldEntry);
		setRootNode(newRoot);
		return oldEntry.get();
	}

	/**
	 * method: removeEntry
	 * purpose: This method is a private helper method that recursively finds the
	 * node to be removed and also sets that node's data to oldEntry
	 */
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T data, ReturnObject oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comp = data.compareTo(rootData);
			if (comp == 0) {
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			} else if (comp < 0) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				rootNode.setLeftChild(removeEntry(leftChild, data, oldEntry));
			} else {
				BinaryNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, data, oldEntry));
			}
		}
		return rootNode;
	}

	/**
	 * method: removeFromRoot
	 * purpose: This method removes the node that is passed in and, depending on
	 * the number of children and which side(s) those children are, relative to
	 * the node to be removed, properly replaces that node to maintain the
	 * correct structure of a binary search tree
	 */
	private BinaryNode<T> removeFromRoot(BinaryNode<T> root) {
		if (root.hasLeftChild() && root.hasRightChild()) {
			BinaryNode<T> leftSubtreeRoot = root.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
			root.setData(largestNode.getData());
			root.setLeftChild(removeLargest(leftSubtreeRoot));
		} else if (root.hasRightChild()) {
			root = root.getRightChild();
		} else {
			root = root.getLeftChild();
		}
		return root;
	}

	/**
	 * method: findLargest
	 * purpose: This method finds the largest leaf in a subtree recursively, given a root node
	 */
	private BinaryNode<T> findLargest(BinaryNode<T> root) {
		if (root.hasRightChild()) {
			root = findLargest(root.getRightChild());
		}
		return root;
	}

	/**
	 * method: removeLargest
	 * purpose: This method removes the largest node in the left subtree and sets that node as
	 * the root node's right child, effectively completing the node replacement after removing
	 * a target node in the tree; this method is a private helper method for the remove method
	 */
	private BinaryNode<T> removeLargest(BinaryNode<T> root) {
		if (root.hasRightChild()) {
			BinaryNode<T> rightChild = root.getRightChild();
			rightChild = removeLargest(rightChild);
			root.setRightChild(rightChild);
		} else {
			root = root.getLeftChild();
		}
		return root;
	}

	private class ReturnObject {
		private T data;

		public ReturnObject(T value) {
			data = value;
		}

		public void set(T value) {
			data = value;
		}

		public T get() {
			return data;
		}
	}

	/**
	 * method: predecessor
	 * purpose: This method finds the predecessor node to a node with a target data if the
	 * data was to be printed out in an inorder traversal of the tree
	 */
	public T predecessor(T data) {
		Iterator<T> inorderIterator = getInorderTraversal();
		if (!contains(data)) {
			return null;
		}
		assert contains(data);
		T prev = inorderIterator.next();
		T comp = inorderIterator.next();
		while (comp != data && inorderIterator.hasNext()) {
			prev = comp;
			comp = inorderIterator.next();
		}
		return prev;
	}

	/**
	 * method: successor
	 * purpose: This method finds the successor node to a node with a target data if the data
	 * was to be printed out in an inorder traversal of a tree
	 */
	public T successor(T data) {
		Iterator<T> inorderIterator = getInorderTraversal();
		if (!contains(data)) {
			return null;
		}
		assert contains(data);
		T result = inorderIterator.next();
		while (result != data) {
			result = inorderIterator.next();
		}
		result = inorderIterator.next();
		return result;
	}

}
