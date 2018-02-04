/**
 * file: BinaryTree.java
 * author: D. Choi
 * class: CS 241 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 10/15/2017
 * 
 * purpose: This parent class is an implementation of the ADT Binary Tree, and has some methods a binary search
 * tree can use without having to rewrite them.
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> implements BinaryTreeInterface<T> {

	private BinaryNode<T> root;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(T rootData) {
		root = new BinaryNode<T>(rootData);
	}

	public T getRootData() {
		T rootData = null;
		if (root != null) {
			rootData = root.getData();
		}
		return rootData;
	}

	public int getHeight() {
		return root.getHeight();
	}

	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void clear() {
		root = null;
	}

	public void setTree(T rootData) {
		root = new BinaryNode<T>(rootData);
	}

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		privateSetTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
	}

	private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new BinaryNode<T>(rootData);
		if (leftTree != null && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}
		if (rightTree != null && !rightTree.isEmpty()) {
			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(rightTree.root.copy());
			}
		}
		if (leftTree != null && leftTree != this) {
			leftTree.clear();
		}
		if (rightTree != null && rightTree != this) {
			rightTree.clear();
		}
	}

	protected void setRootData(T data) {
		root.setData(data);
	}

	protected void setRootNode(BinaryNode<T> newRoot) {
		root = newRoot;
	}

	protected BinaryNode<T> getRootNode() {
		return root;
	}

	public void inorderTraverse() {
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		BinaryNode<T> current = root;
		while (!nodeStack.isEmpty() || current != null) {
			while (current != null) {
				nodeStack.push(current);
				current = current.getLeftChild();
			}
			if (!nodeStack.isEmpty()) {
				BinaryNode<T> next = nodeStack.pop();
				assert next != null;
				System.out.print(next.getData() + " ");
				current = next.getRightChild();
			}
		}
		System.out.println();
	}

	public void preorderTraverse() {
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		BinaryNode<T> current = root;
		while (!nodeStack.isEmpty() || current != null) {
			if (current != null) {
				nodeStack.push(current);
			}
			current = nodeStack.pop();
			System.out.print(current.getData() + " ");
			if (current.hasRightChild()) {
				nodeStack.push(current.getRightChild());
			}
			current = current.getLeftChild();
		}
		System.out.println();
	}

	public void postorderTraverse() {
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		BinaryNode<T> current = root;
		while (!nodeStack.isEmpty() || current != null) {
			while (current != null) {
				if (current.hasRightChild()) {
					nodeStack.push(current.getRightChild());
				}
				nodeStack.push(current);
				current = current.getLeftChild();
			}
			current = nodeStack.pop();
			if (!nodeStack.isEmpty() && current.hasRightChild() && current.getRightChild() == nodeStack.peek()) {
				nodeStack.pop();
				nodeStack.push(current);
				current = current.getRightChild();
			} else {
				System.out.print(current.getData() + " ");
				current = null;
			}
		}
		System.out.println();
	}

	public Iterator<T> getInorderTraversal() {
		return new InorderIterator();
	}

	private class InorderIterator implements Iterator<T> {
		private Stack<BinaryNode<T>> ns;
		private BinaryNode<T> current;

		public InorderIterator() {
			ns = new Stack<>();
			current = root;
		}

		public boolean hasNext() {
			return !ns.isEmpty() || current != null;
		}

		public T next() {
			BinaryNode<T> next = null;
			while (current != null) {
				ns.push(current);
				current = current.getLeftChild();
			}
			if (!ns.isEmpty()) {
				next = ns.pop();
				assert next != null;
				current = next.getRightChild();
			} else {
				throw new NoSuchElementException();
			}
			return next.getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
