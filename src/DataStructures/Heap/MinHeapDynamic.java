package DataStructures.Heap;

import java.util.ArrayList;

public class MinHeapDynamic<T extends Comparable<T>>{

	private ArrayList<T> list;
	private int size;

	public MinHeapDynamic(int initialCapacity){
		list = new ArrayList<>(initialCapacity);
		size = 0;
	}

	public MinHeapDynamic(){
		list = new ArrayList<>();
		size = 0;
	}


	public boolean isMinHeap(){
		return isMinHeap(0);
	}


	public boolean isEmpty(){
		return size == 0;
	}

	public boolean add(T key){
		if (key == null)
			return false;
		list.add(key);
		size++;
		bubbleUp(size-1);
		return true;
	}

	public T extractMin(){
		if (isEmpty())
			throw new RuntimeException("Heap is empty!");
		swap(0,size-1);
		size--;
		bubbleDown(0);
		T key = list.get(size);
		list.remove(size);
		return key;
	}
	
	public T remove(T key){
		if (key==null || !contains(key))
			return null;
		int index = indexOf(key);
		return removeAt(index);
	}

	public T removeAt(int index){
		if (index >= size)
			throw new IndexOutOfBoundsException();
		if (index == 0)
			return extractMin();
		swap(index, size - 1);
		size --;
		T last = list.get(index);
		bubbleUp(index);
		if (list.get(index) == last)
			bubbleDown(index);
		T obj = list.get(size);
		list.remove(size);
		return obj;	
	}

	public boolean contains(T key){
		return indexOf(key) != -1;
	}	

	public int indexOf(T key){
		if (key == null)
			return -1;
		for(int i = 0; i < size; i++)
			if (list.get(i).equals(key))
				return i;
		return -1;
	}

	private int parentIndex(int index){
		return index % 2 == 1? (index -1)/2 : (index -2)/2 ;
	}

	private int rightIndex(int index){
		return 2 * index + 2;
	}

	private int leftIndex(int index){
		return 2 * index + 1;
	}

	private T right(int index){
		int right = rightIndex(index);
		if (right < size)
			return list.get(right);
		return null;
	}

	private T left(int index){
		int left = leftIndex(index);
		if (left < size)
			return list.get(left);
		return null;
	}

	private T parent(int index){
		return list.get(parentIndex(index));
	}

	private void swap(int i, int j){
		T ith = list.get(i);
		T jth = list.get(j);

		list.set(i, jth);
		list.set(j, ith);
	}

	private boolean less(int i, int j){
		if (i >= size || j>=size)
			return true;
		return list.get(i).compareTo(list.get(j)) < 0;
	}

	private boolean lessEqual(int i, int j){
		if (i >= size || j>=size)
			return true;
		return list.get(i).compareTo(list.get(j)) <= 0;
	}

	private void bubbleDown(int index){
		while(index <= size/2 - 1){
			int left = leftIndex(index);
			int smallest = left;
			int right = rightIndex(index);
			if (right < size && less(right,left))
				smallest = right;

			if(!less(smallest,index))
				break;
			swap(smallest,index);
			index = smallest;
		}
	}

	private void bubbleUp(int index){
		int parent = parentIndex(index);
		while(index!=0 && !less(parent,index)){
			swap(parent,index);
			index = parent;
			parent = parentIndex(index);
		}
	}

	private boolean isMinHeap(int index){
		int left = leftIndex(index);
		int right = rightIndex(index);
		if (index > size/2 - 1)
			return true;
		if (less(left,index))
			return false;
		if (right < size && less(right,index))
			return false;
		return isMinHeap(left) && isMinHeap(right);
	}

	@Override public String toString(){
		if (size == 0)
			return "MinHeap(0)";
		StringBuilder sb = new StringBuilder("MinHeap(");
		sb.append(size + ")\n");
		for (int i = 0; i < size; i++){
			sb.append(list.get(i));
			sb.append(" ");
			if (((i + 2) & (i + 1)) == 0)
				sb.append("\n");
		}
		return sb.toString();
	}
}