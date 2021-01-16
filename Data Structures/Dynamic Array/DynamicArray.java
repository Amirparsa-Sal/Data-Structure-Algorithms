import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.Collection;
import java.lang.StringBuilder;

public class DynamicArray<T> implements Iterable<T>,List<T>{

	private int capacity;
	private int size;
	private T[] arr;

	DynamicArray(){
		this(4);
	}

	DynamicArray(int capacity){
		this.capacity = capacity;
		this.size = 0;
		this.arr = (T[]) new Object[capacity];
	}
	
	@Override public T get(int index){
		//Checking bounds
		if (index<0 || index >= size)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");
		return arr[index];
	}

	@Override public int size() {return this.size;}

    @Override public boolean isEmpty() {return size == 0;}

    @Override public boolean contains(Object o) {return indexOf(o)!=-1;}

    @Override public Iterator<T> iterator() {return new ArrayIterator<T>(this);}

    @Override public Object[] toArray() {
        return new Object[0];
    }

    @Override public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override public boolean add(T t) {
    	//Checking if resize is needed. Resize is needed when array is full. each resize doubles the size of the array.
        if (size == capacity){
        	if (size == 0)
        		capacity = 1;
        	else
	        	capacity *= 2;
        	T[] arr = (T[]) new Object[capacity];
        	for(int i = 0; i < size; i++)
        		arr[i] = this.arr[i];	
        	this.arr = arr;
        }
        //Putting object in array
        arr[size] = t;
        size++;
        return true;
    }

    @Override public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1)
        	return false;
        remove(index);
        return true;
    }

    @Override public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override public void clear() {

    }

    @Override public T set(int index, T element) {
    	if (index<=0 || index>=size)
    		throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range!");
    	T prevElement = arr[index];
    	arr[index] = element;
    	return prevElement;
    }

    @Override public void add(int index, T element) {

    }


    @Override public T remove(int index) {
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

    @Override public int indexOf(Object o) {
        for(int i = 0; i < size; i++){
        	if (o == null && arr[i]==null)
        		return i;
       		if(o.equals(arr[i]))
       			return i;
       }
    	return -1;
    }

    @Override public int lastIndexOf(Object o) {
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

    @Override public ListIterator<T> listIterator() {
        return null;
    }

    @Override public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override public List<T> subList(int fromIndex, int toIndex) {
        return null;
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

    	ArrayIterator(DynamicArray<T> arr){
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