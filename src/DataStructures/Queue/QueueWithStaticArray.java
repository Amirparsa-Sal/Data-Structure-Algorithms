package datastructures.queue;

import java.util.Iterator;
import java.lang.StringBuilder;

public class QueueWithStaticArray<T> implements Iterable<T>{

	private T[] list;
	private int front;
	private int rear;
	private int size;
	private int capacity;

	public QueueWithStaticArray(){
		this(16);
	}

	public QueueWithStaticArray(int initialCapacity){
		capacity = initialCapacity;
		list = (T[]) new Object[capacity+1];
		rear = -1;
		front = 0;
		size = 0;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public boolean isFull(){
		return size == capacity;
	}

	public void enQueue(T key){
		if (isFull())
			throw new RuntimeException("Queue is full!");
		rear = (rear+1) % (capacity+1);
		list[rear] = key;
		size++;
		System.out.println("Front: " + front + ", Rear: " + rear);
	}

	public T deQueue(){
		if(isEmpty())
			throw new RuntimeException("Queue is empty!");
		size--;
		T obj = list[front];
		front = (front+1) % (capacity+1);
		return obj;
	}

	public T peek(){
		if(isEmpty())
			throw new RuntimeException("Queue is empty!");
		return list[front];
	}

	@Override public String toString(){
		if (size == 0)
			return "[]";
		if (size == 1)
			return "Queue(1)[" + peek().toString() + "]";
		StringBuilder sb = new StringBuilder("Queue(" + size() + ")[");
		for (int i = front; i != rear; i = ((i % capacity) + 1) % capacity){
			sb.append(list[i]);
			sb.append(" <- ");
		}
		sb.append(list[rear]);
		sb.append("]");
		return sb.toString();
	}

	private T getAt(int index){
		return list[index];
	}

	@Override public Iterator<T> iterator(){
		return new QueueIterator<T>(this);
	}

	private class QueueIterator<T> implements Iterator<T>{
		
		private QueueWithStaticArray<T> queue;
		private int index;
		private int iterated;
		
		QueueIterator(QueueWithStaticArray<T> queue){
			index = front;
			iterated = 0;
			this.queue = queue;
		}

    	@Override public boolean hasNext() {return size != iterated;}

    	@Override public T next() {
    		if (!hasNext())
    			throw new IndexOutOfBoundsException("Index " + index + " out of range!");
    		T result = queue.getAt(index);
    		index = ((index % capacity) + 1) % capacity;
    		iterated++;
    		return result;
    	}

    	@Override public void remove() {
    		throw new UnsupportedOperationException("This operation is not supported!");	
    	}
	}
}
