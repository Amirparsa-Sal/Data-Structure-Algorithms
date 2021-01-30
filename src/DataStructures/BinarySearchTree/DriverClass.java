package DataStructures.BinarySearchTree;

import java.util.Iterator;

public class DriverClass{

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();

		bst.add(4);
		bst.add(3);
		bst.add(1);
		bst.add(6);
		bst.add(5);
		bst.add(8);

		Iterator<Integer> it = bst.inOrderIterator();
		bst.traverse();
		System.out.println("--------------");
		while(it.hasNext()){
			Integer i = it.next();
			System.out.println(i);
		}

	}
}