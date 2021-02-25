package datastructures.linkedlist;

import java.util.Iterator;

public class DriverClass{

	public static void main(String[] args) {
		// SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		// list.addLast(1);
		// System.out.println(list);
		// list.addLast(2);
		// System.out.println(list);
		// list.addLast(3);
		// System.out.println(list);
		// Iterator<Integer> it = list.iterator();
		// while(it.hasNext()){
		// 	Integer i = it.next();
		// 	if (i<=2)
		// 		it.remove();
		// }
		// System.out.println(list);

		// DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		// list.addLast(1);
		// System.out.println(list);
		// list.addLast(2);
		// System.out.println(list);
		// list.addLast(3);
		// System.out.println(list);
		// Iterator<Integer> it = list.iterator();
		// while(it.hasNext()){
		// 	Integer i = it.next();
		// 	if (i<=2)
		// 		it.remove();
		// }
		// System.out.println(list);
		// list.removeLast();
		// System.out.println(list);
		
		CircularLinkedList<Integer> list = new CircularLinkedList<>();
		list.addLast(1);
		System.out.println(list);
		list.addLast(2);
		System.out.println(list);
		list.addLast(3);
		System.out.println(list);
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			Integer i = it.next();
			if (i<=2)
				it.remove();
		}
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
	}
}