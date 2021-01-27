package DataStructures.UnionFind;

import java.lang.StringBuilder;

/**
	Represents a class for UnionFund(Disjoint Set) data structure.
	Before using this class, we need a mapping between our objects and integers in range [0,n).

	@author Amirparsa Salmankhah 
*/
public class UnionFind{

	//Stores the size of each group. group size is stored in root node.
	private int[] sizes;
	//Stores parents of each node.
	private int[] parents;
	//Stores number of the elements
	private int size;
	//Stores number of the groups
	private int groups;

	public UnionFind(int capacity){
		if (capacity <= 0)
			throw new IllegalArgumentException("Invalid size!");
		parents = new int[capacity];
		sizes = new int[capacity];
		size = capacity;
		groups = capacity;

		for(int i = 0; i < capacity; i++){
			parents[i] = i;
			sizes[i] = 1;
		}
	}

	/**
		Finds the parent of the index.

		@return parent index
	*/
	public int find(int index){
		if (index < 0)
			throw new IllegalArgumentException("Invalid index!");
		if (index >= size)
			throw new RuntimeException("Index out of range!");
		int parent = index;
		while(parents[parent] != parent){
			parent = parents[parent];
		}

		//Path compression to optimize 
		while(index != parent){
			int tmpParent = parents[index];
			parents[index] = parent;
			index = tmpParent;
		}

		return parent;
	}

	/**
		Checks if two objects are unified.

		@return true if yes and false if not.
	*/
	public boolean isUnified(int i, int j){return find(i) == find(j);}

	/**
		finds size of the group which index is there.

		@return size of the group which index is there.
	*/
	public int groupSize(int index){return sizes[find(index)];}

	/**
		Returns number of the groups.

		@return number of the groups.
	*/
	public int groups(){return groups;}

	/**
		Unifies two objects i and j.
	*/
	public void unify(int i, int j){
		if (isUnified(i,j))
			return;
		int parentI = parents[i];
		int parentJ = parents[j];
		if (sizes[parentI] < sizes[parentJ]){
			parents[parentI] = parentJ;
			sizes[parentJ] += sizes[parentI];	
		}
		else{
			parents[parentJ] = parentI;
			sizes[parentI] += sizes[parentJ];	
		}
		groups--;
	}

	@Override public String toString(){
		StringBuilder sb = new StringBuilder("UnionFind(");
		for (int i = 0; i < size; i++)
			sb.append(i + "->" + parents[i] + " ");
		sb.append(")");
		return sb.toString();
	}
}