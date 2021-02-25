package datastructures.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class BinaryMinHeapOptimized<T extends Comparable<T>>{

	private ArrayList<T> list;
	private Map<T, TreeSet<Integer>> map;
	private int size;

	public BinaryMinHeapOptimized(int initialCapacity){
		if (initialCapacity <= 0)
			throw new IllegalArgumentException("Invalid size!");
		list = new ArrayList<>(initialCapacity);
		map = new HashMap<>();
		size = 0;
	}

	public BinaryMinHeapOptimized(){
		this(1);
	}

	public BinaryMinHeapOptimized(T[] arr){
		size = arr.length;
		list = new ArrayList<>(size);
		for(int i = 0; i < size; i++){
			list.add(arr[i]);
			addMap(arr[i],i);
		}
		for(int i = Math.max(0,size / 2 - 1); i >= 0; i--)
			bubbleDown(i); 
	}

	public BinaryMinHeapOptimized(Collection<T> arr){
		size = arr.size();
		list = new ArrayList<>(size);
		int index = 0;
		for (T key : arr){
			list.add(key);
			addMap(key, index);
			index++;
		}
		for(int i = Math.max(0,size / 2 - 1); i >= 0; i--)
			bubbleDown(i);
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
		addMap(key, size);
		size++;
		bubbleUp(size-1);
		return true;
	}

	public T extractMin(){
		return removeAt(0);
	}
	
	public T remove(T key){
		if (key==null || !contains(key))
			return null;
		int index = indexOf(key);
		return removeAt(index);
	}

	private T removeAt(int index){
		if (index >= size)
			throw new IndexOutOfBoundsException();
		swap(index, size - 1);
		size --;
		T last = list.get(index);
		bubbleDown(index);
		if (list.get(index) == last)
			bubbleUp(index);
		T obj = list.get(size);
		list.remove(size);
		removeMap(obj, size);
		//needs to be removed from map
		return obj;	
	}

	public boolean contains(T key){
		return indexOf(key) != null;
	}	

	public Integer indexOf(T key){
		if (key == null)
			return -1;
		return getMap(key);
	}

	private int parentIndex(int index){return (index - 1) / 2;}

	private int rightIndex(int index){return 2 * index + 2;}

	private int leftIndex(int index){return 2 * index + 1;}

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
		swapMap(i,j);
		list.set(i, jth);
		list.set(j, ith);
	}

	private boolean less(int i, int j){
		if (i >= size || j>=size)
			return true;
		return list.get(i).compareTo(list.get(j)) < 0;
	}

	private void bubbleDown(int index){
		int nonLeaf = size/2 - 1;
		while(index <= nonLeaf){
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

	private void addMap(T key, int index){
		TreeSet<Integer> set = map.get(key);
		if (set != null)
			set.add(index);
		else{
			set = new TreeSet<>();
			set.add(index);
			map.put(key,set);
		}
	}

	private void swapMap(int i, int j){
		T ith = list.get(i);
		T jth = list.get(j);
		map.get(ith).remove(i);
		map.get(jth).remove(j);
		map.get(ith).add(j);
		map.get(jth).add(i);
	}

	private Integer getMap(T key) {
	    TreeSet<Integer> set = map.get(key);
	    if (set != null)
	   		return set.last();
	    return null;
  	}

  	private void removeMap(T key, int index){
  		TreeSet<Integer> set = map.get(key);
  		set.remove(index);
  		if (set.size() == 0)
  			map.remove(key);
  	}
}