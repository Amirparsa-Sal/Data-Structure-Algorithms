package DataStructures.BinarySearchTree;

import java.util.Iterator;

public class DriverClass{

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();

		bst.add(5);
		bst.add(2);
		bst.add(3);
		bst.add(8);
		bst.add(6);
		bst.add(9);

		Iterator<Integer> it = bst.inOrderIterator();
		while(it.hasNext()){
			Integer i = it.next();
			System.out.println(i);
		}

	}
}