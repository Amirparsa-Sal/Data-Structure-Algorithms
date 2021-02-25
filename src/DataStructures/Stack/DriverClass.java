package datastructures.stack;

public class DriverClass{

	public static void main(String[] args) {
		StackWithLinkedList<Integer> list = new StackWithLinkedList<>();
		System.out.println(list);
		list.push(1);
		System.out.println(list);
		list.push(2);
		System.out.println(list);
		list.pop();
		System.out.println(list);
	}
}