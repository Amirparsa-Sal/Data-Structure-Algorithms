package datastructures.trees.fenwicktree;

import java.lang.StringBuilder;

public class FenwickTree{

	private long[] fenwickList;
	private int size;

	public FenwickTree(int size){
		if (size <= 0)
			throw new IllegalArgumentException("Invalid size!");
		this.size = size + 1;
		fenwickList = new long[size+1];
	}

	public FenwickTree(long[] arr){
		if (arr == null)
			throw new IllegalArgumentException("Array cant be null!");
		size = arr.length + 1;
		fenwickList = new long[size];
		for (int i = 1; i < size; i++)
			fenwickList[i] = arr[i-1];
		//needs to update
		for (int i = 1; i < size; i++){
			int parent = i + lsb(i);
			if (parent < size)
				fenwickList[parent] += fenwickList[i];
		}
	}

	private int lsb(int num){
		return num & -num;
	}

	public long prefixSum(int index){
		long sum = 0l;
		int i = index;
		while(i != 0){
			sum += fenwickList[i];
			i -= lsb(i);
		}
		return sum;
	}

	public long rangeSum(int startIndex, int endIndex){
		if (startIndex < 1 || endIndex < 1)
			throw new IllegalArgumentException("Invalid index!");
		if (endIndex < startIndex)
			throw new IllegalArgumentException("End index annot be less than start index!");
		return prefixSum(endIndex) - prefixSum(startIndex-1);
	}

	public void increaseKey(int keyIndex, long addition){
		if (keyIndex < 1)
			throw new IllegalArgumentException("Invalid index!");
		int i = keyIndex;
		while(i < size){
			fenwickList[i] += addition;
			i += lsb(i);
		}
	}

	public void changeKey(int keyIndex, long newKey){
		increaseKey(keyIndex, newKey - getAt(keyIndex));
	}

	public long getAt(int keyIndex){
		if (keyIndex < 1)
			throw new IllegalArgumentException("Invalid index!");
		return rangeSum(keyIndex, keyIndex);
	}

	public long[] getArray(){
		long[] list = new long[size - 1];
		for (int i = 0; i < size - 1; i++)
			list[i] = getAt(i + 1);
		return list;
	}

	@Override public String toString(){
		if(size == 1)
			return new String("FenwickTree[" + fenwickList[1] + "]");
		StringBuilder sb = new StringBuilder("FenwickTree[");
		for(int i = 1; i < size - 1; i++)
			sb.append(fenwickList[i] + ", ");
		sb.append(fenwickList[size-1] + "]");
		return sb.toString();
	}

}