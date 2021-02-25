package datastructures.trees.avltree;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Stack;

public class AVLTree<T extends Comparable<T>>{

	private Node root;
	private int size;

	public AVLTree(){
		root = null;
		size = 0;
	}

	public AVLTree(T key){
		root = new Node(key);
		size = 1;
	}

	private boolean less(T key1, T key2){return key1.compareTo(key2) <= 0;} 

	private Node find(T key){
		if (key == null)
			return null;
		Node node = root;
		while(node != null && !node.key.equals(key)){
			if (less(key, node.key))
				node = node.left;
			else
				node = node.right;
		}
		return node;
	} 

	private void leftRotate(Node node){
		Node parent = node.parent;
		Node grandParent = parent.parent;
		if(grandParent != null){
			if (parent == grandParent.left)
				grandParent.left = node;
			else
				grandParent.right = node;
		}
		else
			root = node;
		node.parent = grandParent;
		parent.right = node.left;
		if (node.left!= null)
			node.left.parent = parent;
		parent.parent = node;
		node.left = parent;
		updateFactor(parent);
		updateFactor(node);
	}

	private void rightRotate(Node node){
		Node parent = node.parent;
		Node grandParent = parent.parent;
		if(grandParent != null){
			if (parent == grandParent.left)
				grandParent.left = node;
			else
				grandParent.right = node;
		}
		else
			root = node;
		node.parent = grandParent;
		parent.left = node.right;
		if (node.right!= null)
			node.right.parent = parent;
		parent.parent = node;
		node.right = parent;
		updateFactor(parent);
		updateFactor(node);
	}


	public boolean insert(T key){
		if (key == null)
			return false;
		if(root == null){
			root = new Node(key);
		}
		else
			insert(root,key);
		size++;
		return true;
	}

	private void insert(Node node, T key){
		if(less(key,node.key)){
			if (node.left == null){
				node.left = new Node(key);
				node.left.parent = node;
			}
			else
				insert(node.left,key);
		}
		else{
			if (node.right == null){
				node.right = new Node(key);
				node.right.parent = node;
			}
			else
				insert(node.right,key);
		}
		updateFactor(node);
		if(node.factor == -2 || node.factor == 2)
			balance(node);
	}

	private void updateFactor(Node node){
		int leftHeight = -1;
		int rightHeight = -1;
		if(node.left!=null)
			leftHeight = node.left.height;
		if(node.right!=null)
			rightHeight = node.right.height;
		node.height = 1 + Math.max(leftHeight,rightHeight);
		node.factor = rightHeight - leftHeight;
	}

	private void balance(Node node){
		if (node.factor == -2){
			if(node.left.factor > 0)
				leftRotate(node.left.right);
			rightRotate(node.left);
		} 
		else if (node.factor == 2){
			if(node.right.factor < 0)
				rightRotate(node.right.left);
			leftRotate(node.right);
		}
	}

	public void inOrderTraversal(){
		if(root == null)
			return;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				Node node = queue.remove();
				if(node == null)
					System.out.print("null ");	
				else
					System.out.print(node.key + " ");
				if(node != null){
					queue.add(node.left);
					queue.add(node.right);
				}
			}	
			System.out.println();
		}
	}

	public Iterator<T> inOrderIterator(){
		Stack<Node> stack = new Stack<>();
		if (size != 0)
			stack.push(root);
		Node node = root;
		while(node != null && node.left!=null){
			stack.push(node.left);
			node = node.left;
		}
		return new Iterator<T>(){
			
			@Override public boolean hasNext(){return !stack.isEmpty();}

			@Override public T next(){
				Node next = stack.pop();
				if (next.right != null){
					stack.push(next.right);
					Node node = next.right;
					while(node != null && node.left!=null){
						stack.push(node.left);
						node = node.left;
					}
				}
				return next.key;
			}

			@Override public void remove(){
				throw new UnsupportedOperationException();
			}
		};
	}

	public Iterator<T> preOrderIterator(){
		Stack<Node> stack = new Stack<>();
		if (size != 0)
			stack.push(root);

		return new Iterator<T>(){
			
			@Override public boolean hasNext(){return !stack.isEmpty();}

			@Override public T next(){
				Node next = stack.pop();
				if (next.right != null)
					stack.push(next.right);
				if(next.left != null)
					stack.push(next.left);
				return next.key;
			}

			@Override public void remove(){
				throw new UnsupportedOperationException();
			}
		};
	}

	public Iterator<T> postOrderIterator(){
		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		if (size != 0)
			stack1.push(root);
		while(!stack1.isEmpty()){
			Node node = stack1.pop();
				if (node != null){
				stack2.push(node);
				if (node.left != null) 
					stack1.push(node.left);
				if (node.right != null)
					stack1.push(node.right);
			}
		}

		return new Iterator<T>(){
			
			@Override public boolean hasNext(){return !stack2.isEmpty();}

			@Override public T next(){
				return stack2.pop().key;
			}

			@Override public void remove(){
				throw new UnsupportedOperationException();
			}
		};
	}


	private class Node{

		T key;	
		Node parent;
		Node left;
		Node right;
		int factor;
		int height;

		Node(Node left, T key, Node right){
			factor = 0;
			height = 0;
			this.key = key;
			this.left = left;
			this.right = right;
			parent = null;
		}

		Node(){
			this(null, null, null);
		}

		Node(T key){
			this(null, key, null);
		}


		Node(Node left, T key){
			this(left, key, null);
		}

		Node(T key, Node right){
			this(null, key, right);
		}
	}

}