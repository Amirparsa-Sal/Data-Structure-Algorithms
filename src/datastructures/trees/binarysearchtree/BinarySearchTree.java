package datastructures.trees.binarysearchtree;

import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>>{

	private Node root;

	private int size;

	
	public BinarySearchTree(){
		root = null;
		size = 0;
	}

	public BinarySearchTree(T key){
		root = new Node(key);
		root.parent = null;
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

	public boolean contains(T key){return find(key)!=null;}

	public boolean isEmpty(){return size == 0;}

	public int size(){return size;}

	public void add(T key){
		if (size == 0){
			root = new Node(key);
			root.parent = null;
			size++;
			return;
		}
		Node node = root, prev = null;
		while (node != null){
			prev = node;
			if (less(key,node.key))
				node = node.left;
			else
				node = node.right;
		}
		if (less(key, prev.key)){
			prev.left = new Node(key);
			prev.left.parent = prev;
		}
		else{
			prev.right = new Node(key);
			prev.right.parent = prev;
		}
		size++;
	}

	public boolean remove(T key){
		Node node = find(key);
		if (node == null)
			return false;
		size--;
		return remove(node);
	}

	private boolean remove(Node node){
		if (node.right == null)
			transplant(node, node.left);
		else if (node.left == null)
			transplant(node, node.right);
		else{
			Node successor = min(node.right);
			node.key = successor.key;
			remove(successor);
		}
		return true;
	}

	private void transplant(Node node, Node replace){
		if (node.parent == null)
			root = replace;
		else if (node == node.parent.left)
			node.parent.left = replace;
		else
			node.parent.right = replace;
		if(replace != null)
			replace.parent = node.parent;
	}

	private Node successor(Node node){
		if (node.right != null)
			return min(node.right);
		Node parent = node.parent, trav = node;
		while (parent != null && trav == trav.parent.right){
			parent = parent.parent;
			node = node.parent;
		}
		return parent;
	}

	private Node predecessor(Node node){
		if (node.left != null)
			return max(node.left);
		Node parent = node.parent, trav = node;
		while (parent != null && trav == trav.parent.left){
			parent = parent.parent;
			node = node.parent;
		}
		return parent;
	}
	
	private Node min(Node node){
		Node trav = node;
		while(trav.left != null)
			trav = trav.left;
		return trav;
	}

	private Node max(Node node){
		Node trav = node;
		while(trav.right != null)
			trav = trav.right;
		return trav;
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

		Node(Node left, T key, Node right){
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