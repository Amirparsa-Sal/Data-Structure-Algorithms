package datastructures.dynamicarray;

import java.util.Iterator;
import java.util.List;
import java.lang.StringBuilder;
import java.util.Arrays;

public class DynamicArray<T> implements Iterable<T>{

	private int capacity;
	private int size;
	private T[] arr;

	public DynamicArray(){
		this(4);
	}

	public DynamicArray(int capacity){
		this.capacity = capacity;
		this.size = 0;
		this.arr = (T[]) new Object[capacity];
	}
	
	public T get(int index){
		//Checking bounds
		if (index<0 || index >= size)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");
		return arr[index];
	}

	public int size() {return this.size;}

    public boolean isEmpty() {return size == 0;}

    public boolean contains(Object o) {return indexOf(o)!=-1;}

    @Override public Iterator<T> iterator() {return new ArrayIterator<T>(this);}

    public Object[] toArray() {
    	Object[] arr = new Object[size];
    	for(int i = 0; i < size; i++)
    		arr[i] = this.arr[i];
        return arr;
    }

    public <E> E[] toArray(E[] a) {
    	//if a has not enough space return new array
    	if(a.length < size)
    		return (E[]) Arrays.copyOf(arr, size, a.getClass());
    	//filling a if a has enough space
		System.arraycopy(arr,0,a,0,size);
		if(a.length>size)
			a[size] = null;
		return a;
	}

    public boolean add(T t) {
    	//Checking if resize is required. Resize is required when the array is full. each resize doubles the size of the array.
        if (size == capacity)
        	addCapacity();
        //Putting object in array
        arr[size] = t;
        size++;
        return true;
    }

    public void add(int index, T element) {
    	//checking if index is in range
    	if (index<0 || index>=size)
    		throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");
    	//Checking if resize is required. Resize is required when the array is full. each resize doubles the size of the array.
    	if (size==capacity)
    		addCapacity();
    	for(int i = size; i > index; i--)
    		arr[i] = arr[i-1];
    	arr[index] = element;
    	size++;
    }

    private void addCapacity(){
    	if (size == 0)
    		capacity = 1;
    	else
        	capacity *= 2;
    	T[] arr = (T[]) new Object[capacity];
    	System.arraycopy(this.arr,0,arr,0,size);	
    	this.arr = arr;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1)
        	return false;
        remove(index);
        return true;
    }

    public void clear() {
    	capacity = 4;
    	arr = (T[]) new Object[capacity];
    	size = 0;
    }

    public T set(int index, T element) {
    	if (index<=0 || index>=size)
    		throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");
    	T prevElement = arr[index];
    	arr[index] = element;
    	return prevElement;
    }



    public T remove(int index) {
    	//Checking bound
    	if (index<0 || index>=size)
    		throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");
    	//Checking if resize is needed. Resize happens when array size is 1/4 of the capacity.
    	T[] arr = this.arr;
        if (size-1 == capacity/4){
        	capacity /= 2;
        	this.arr = (T[]) new Object[capacity];
        }
        //Deleting and shifting
        T result = null;
        for (int i = 0, j = 0; i < size; i++, j++){
        	if (i == index){
        		j--;
        		result = arr[i];
        	}
        	else
        		this.arr[j] = arr[i];
        }
        size--;
        return result;
    }

    public int indexOf(Object o) {
        for(int i = 0; i < size; i++){
        	if (o == null && arr[i]==null)
        		return i;
       		if(o.equals(arr[i]))
       			return i;
       }
    	return -1;
    }

    public int lastIndexOf(Object o) {
    	if (size == 0)
    		return -1;
        for(int i = size-1; i >= 0; i++){
        	if (o == null && arr[i]==null)
        		return i;
       		if(o.equals(arr[i]))
       			return i;
       }
    	return -1;
    }

    @Override public String toString(){
    	if (size == 0)
    		return new String("[]");
    	StringBuilder sb = new StringBuilder("Array(" + size + ")[");
    	for (int i = 0; i < size-1; i++)
    		sb.append(arr[i] + ", ");
    	return sb.append(arr[size-1] + "]").toString();
    }


    private class ArrayIterator<T> implements Iterator<T>{
    	
    	private DynamicArray<T> arr;
    	private int index;

    	public ArrayIterator(DynamicArray<T> arr){
    		this.arr = arr;
    		index = 0;
    	}

    	@Override public boolean hasNext() {return index!=arr.size;}

    	@Override public T next() {
    		if (!hasNext())
    			throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");
    		T result = arr.get(index);
    		index++;
    		return result;
    	}

    	@Override public void remove() {
    		if (index == size)
    			throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");	
    		arr.remove(index);
    	}
    }

}