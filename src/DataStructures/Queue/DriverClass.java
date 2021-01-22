package DataStructures.Queue;

public class DriverClass{

	public static void main(String[] args) {
		QueueWithLinkedList<Integer> list = new QueueWithLinkedList<>();
		System.out.println(list);
		list.enQueue(1);
		System.out.println(list);
		list.enQueue(2);
		System.out.println(list);
		list.deQueue();
		System.out.println(list);
	}
}