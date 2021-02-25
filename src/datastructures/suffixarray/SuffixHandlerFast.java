package datastructures.suffixarray;

import java.util.HashMap;

public class SuffixHandlerFast{

	private int[] suffixArray;
	private String s;
	private HashMap<Character, Integer> map;

	public SuffixHandlerFast(String s){
		if(s == null)
			throw new IllegalArgumentException("String cannot be null!");
		this.s = s;
		suffixArray = new int[s.length()];
		map = new HashMap<>();
		createMap();
	}

	// m i s s i s s i p p i
	// 0 1 2 3 4 5 6 7 8 9 10

	// 1 4 7 10 2 5 8
	// 0 1 2 3  4 5 6 

	// 0 3 6 9
	// 0 1 2 3

	public int[] getSuffixArray(){
		return suffixArray;
	}

	public void construct(){
		int size = s.length();
		//build SA12
		Suffix[] SA12 = buildSA12();
		for (Suffix s : SA12)
			System.out.println(s);
		int size12 = SA12.length;
		int[] rank12 = new int[size12];
		int[] SA1 = new int[(size + 1) / 3];
		int ind = 0;
		for(int i = 0; i < size12; i++){
			rank12[indexToindex12(SA12[i].index, size12, false)] = i;
			if (SA12[i].index % 3 == 1)
				SA1[ind++] = SA12[i].index;
		}
		System.out.println();
		for (Integer i : rank12)
			System.out.println(i);

		System.out.println();
		for (Integer i : SA1)
			System.out.println(i);
		//build SA0
		int size0 = (size+ 2) / 3;
		Suffix[] SA0 = new Suffix[size0];
		int [] SA0Ranks = new int[size0];
		for(int i = 0; i < size0 - 1; i++){
			Suffix suf = new Suffix((SA1[i] - 1));
			SA0[i] = suf;
			SA0Ranks[i] = suf.ranks[0]; 
		}
		//handling last SA0 element
		if (size % 3 == 1){
			for(int i = size0 - 1; i >= 1; i--){
				SA0[i] = SA0[i - 1];
				SA0Ranks[i] = SA0Ranks[i - 1];
			}
			Suffix suf = new Suffix(size - 1);
			SA0[0] = suf;
			SA0Ranks[0] = suf.ranks[0];
		}
		else{
			int index = size0 - 1;
			Suffix suf = new Suffix((SA1[index] - 1));
			SA0[index] = suf;
			SA0Ranks[index] = suf.ranks[0];
		}

		int[] sortedRanks = radixSort(SA0Ranks);
		SA0 = updateOrders(sortedRanks, SA0);
		
		System.out.println("SA0");
		for (Suffix s : SA0)
			System.out.println(s);
		System.out.println();
		// 9 -> 3 10 -> 4 11 -> 4 12 -> 4

		suffixArray = new int[size];
		int index0 = 0;
		int index12 = 0;
		int index = 0;
		while(index0 < size0 && index12 < size12){
			if (mergeChecking(SA12[index12].index, SA0[index0].index, rank12))
				suffixArray[index++] = SA12[index12++].index;
			else
				suffixArray[index++] = SA0[index0++].index;
		}
		if (index12 == size12)
			for(int i = index0; i < size0; i++)
				suffixArray[index++] = SA0[i].index;
		else
			for(int i = index12; i < size12; i++)
				suffixArray[index++] = SA12[i].index;
	}

	//checks if i less than j
	// j mod 3 = 0;
	// i mod 3 = 1 or 2;
	private boolean mergeChecking(int i, int j, int[] SA12Ranks){
		int size = s.length();
		int size12 = size * 2 / 3;
		int i12 = indexToindex12(i, size12, false);
		int orderI = map.get(getCharAt(i));
		int orderJ = map.get(getCharAt(j));
		if (i % 3 == 1){
			if(orderI > orderJ)
				return false;
			if(orderI < orderJ)
				return true;
			int rankI = i == size - 1 ? 0 : SA12Ranks[indexToindex12(i + 1,size12,false)];
			int rankJ = j == size - 1 ? 0 : SA12Ranks[indexToindex12(j + 1,size12,false)];
			return rankI <= rankJ;
		}
		if (i % 3 == 2){
			if(orderI > orderJ)
				return false;
			if(orderI < orderJ)
				return true;
			int orderII = map.get(getCharAt(i + 1));
			int orderJJ = map.get(getCharAt(j + 1));
			if(orderII > orderJJ)
				return false;
			if(orderII < orderJJ)
				return true;
			int rankI = i >= size - 2 ? 0 : SA12Ranks[indexToindex12(i + 2,size12,false)];
			int rankJ = j >= size - 2 ? 0 : SA12Ranks[indexToindex12(j + 2,size12,false)];
			return rankI <= rankJ;
		}
		return false;
	}

	private Suffix[] buildSA12(){
		int size = s.length() * 2 / 3;
		int[] index12 = new int[size];
		int index = 0;
		for(int i = 1; i < s.length(); i+=3)
			index12[index++] = i;
		for(int i = 2; i < s.length(); i+=3)
			index12[index++] = i;
		Suffix[] SA12 = new Suffix[size];
		for(int i = 0; i < size; i++)
			SA12[i] = new Suffix(index12[i]);
		
		//sort SA12
		SA12 = sortSuffixes(SA12);

		//make lexicographical orders and check for duplicates.
		int order = 1;
		int[] lexOrders = new int[size];
		lexOrders[0] = 1;
		for(int i = 1; i < size; i++){
			if(!SA12[i].isSameRankedWith(SA12[i-1]))
				order++;
			lexOrders[i] = order;
		}

		for(Suffix s : SA12)
			System.out.println(s);
		System.out.println("----------------");
		//try recursion if has duplicates
		if (order < size){
			char[] augmentedRanks = new char[size + 1];
			for(int i = 0; i < size; i++){
				index = SA12[i].index;
				augmentedRanks[indexToindex12(index, size, true)] = (char)(lexOrders[i] + '0');
			}
			augmentedRanks[(size + 1) / 2] = '#';
			System.out.println(new String(augmentedRanks));
			SuffixHandlerFast shf = new SuffixHandlerFast(new String(augmentedRanks));
			Suffix[] suffixes = shf.getAllSuffixes();
			for(Suffix s : suffixes)
			System.out.println(s);
			System.out.println("----------------");
			suffixes = sortSuffixes(suffixes);
			SA12 = new Suffix[size];
			for(int i = 0; i < size; i++)
				SA12[i] = new Suffix(index12Toindex(suffixes[i+1].index,size));
		}
		return SA12;
	}

	// m i s s i s s i p p i
	// 0 1 2 3 4 5 6 7 8 9 10

	// 1 4 7 10 2 5 8
	// 0 1 2 3  4 5 6 
	private int index12Toindex(int i, int size){
		if (i < (size + 1) / 2)
			return 3 * i + 1;
		return (i - 1 - (size+1) /2) * 3 + 2; 
	} 

	private int indexToindex12(int i, int size, boolean hasHashSign){
		if (i % 3 == 1)
			return (i - 1) / 3;
		return hasHashSign ? (size + 1) / 2 + (i - 2) / 3 + 1 : (size + 1) / 2 + (i - 2) / 3; 
	} 

	private Suffix[] sortSuffixes(Suffix[] suffixes){
		int[] indexes;
		int size = suffixes.length;
		for(int j = 2; j >= 0; j--){
			int[] ranks = new int[size];
			for(int i = 0; i < size; i++)
				ranks[i] = suffixes[i].ranks[j];
			indexes = radixSort(ranks);;
			suffixes = updateOrders(indexes, suffixes);
		}	
		return suffixes;
	}

	private Suffix[] updateOrders(int[] sortedIndexes, Suffix[] suffixes){
		int size = suffixes.length;
		Suffix[] newSuffixes = new Suffix[size];
		for(int i = 0; i < size; i++)
			newSuffixes[i] = suffixes[sortedIndexes[i]];
		return newSuffixes;
	}

	private Suffix[] getAllSuffixes(){
		Suffix[] suffixes = new Suffix[s.length()];
		for(int i = 0; i < s.length(); i++)
			suffixes[i] = new Suffix(i);
		return suffixes;
	}

	private void createMap(){
		int[] asciCodes = new int[s.length()];
		for(int i = 0; i < s.length(); i++)
			asciCodes[i] = (int)(s.charAt(i));
		int[] sortedIndexes = radixSort(asciCodes);
		int value = 1;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(sortedIndexes[i]);
			if (!map.containsKey(c))
				map.put(c,value++);
		} 	
		map.put('$',0);
		map.put('#',0);
	}

	public void printMap(){
		for(char c : map.keySet())
			System.out.print(c + ":" + map.get(c) + " ");
		System.out.println();
	}

	private char getCharAt(int index){
		if (index >= s.length())
			return '$';
		return s.charAt(index);
	}

	private int[] countingSort(int[] arr, int[] indexes, int exp){
		int size = arr.length;
		int[] counts = new int[10];
		int[] digits = new int[size];
		for(int i = 0; i < size; i++)
			digits[i] = (arr[i] / exp) % 10;
		for(int i = 0; i < size; i++)
			counts[digits[i]]++;
		for(int i = 1; i < 10; i++)
			counts[i] += counts[i-1];
		for(int i = 9; i > 0; i--)
			counts[i] = counts[i-1];
		counts[0] = 0;
		int[] newIndexes = new int[size];
		int[] newArr = new int[size];
		for(int i = 0; i < size; i++){
			int dig = counts[digits[i]]++;
			newIndexes[dig] = indexes[i];
			newArr[dig] = arr[i];
		}
		for(int i = 0; i < size; i++)
			arr[i] = newArr[i];
		return newIndexes;
	}

	public int[] radixSort(int[] arr){
		int size = arr.length;
		int max = Integer.MIN_VALUE;
		int[] sortedIndexes = new int[size];
		for(int i = 0; i < size; i++){
			sortedIndexes[i] = i;
			if (arr[i] > max)
				max = arr[i];
		}
		int exp = 1;
		for(int i = 0; i < len(max); i++){
			sortedIndexes = countingSort(arr, sortedIndexes, exp);
			exp *= 10;
		}
		return sortedIndexes;
	}

	private int len(int number){
		int size = 0;
		for(size = 0; number > 0; number/=10, size++);
		return size;
	}

	public int digit(int number, int i){
		return (number / (int) Math.pow(10, i + 1)) % 10;
	}

	private class Suffix{

		int index;
		int[] ranks;

		Suffix(int index){
			this.index = index;
			ranks = new int[3];
			for(int i = 0; i < 3; i++)
				ranks[i] = map.get(getCharAt(index + i));
		}

		boolean isSameRankedWith(Suffix s){
			return ranks[2] == s.ranks[2] &&
				   ranks[1] == s.ranks[1] &&
				   ranks[0] == s.ranks[0]; 
		}

		@Override public String toString(){
			return new String(index + ": " + ranks[0] + " " + ranks[1] + " " + ranks[2]);
		}
	}

}