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
		construct();
	}

	public void construct(){
		Suffix[] SA12 = buildSA12();
		System.out.println("----------------------------");
		for(Suffix s : SA12)
			System.out.println(s);
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
		//try recursion if has duplicates
		if (order < size){
			char[] augmentedRanks = new char[size + 1];
			for(int i = 0; i < size; i++){
				index = SA12[i].index;
				if (index % 3 == 1)
					augmentedRanks[(index - 1) / 3] = (char)(lexOrders[i] + '0');
				else
					augmentedRanks[(size + 1) / 2 + (index - 2) / 3 + 1] = (char)(lexOrders[i] + '0');
			}
			augmentedRanks[(size + 1) / 2] = '#';
			System.out.println(new String(augmentedRanks));
			SuffixHandlerFast shf = new SuffixHandlerFast(new String(augmentedRanks));
			Suffix[] suffixes = shf.getAllSuffixes();
			suffixes = sortSuffixes(suffixes);
			SA12 = new Suffix[size];
			for(int i = 0; i < size; i++)
				SA12[i] = new Suffix(index12Toindex(suffixes[i+1].index,size));
		}
		return SA12;
	}

	private int index12Toindex(int i, int size){
		if (i < (size + 1) / 2)
			return 3 * i + 1;
		return (i - 1 - (size+1) /2) * 3 + 2; 
	} 

	private Suffix[] sortSuffixes(Suffix[] suffixes){
		int[] indexes;
		int size = suffixes.length;
		for(int j = 2; j >= 0; j--){
			int[] ranks = new int[size];
			for(int i = 0; i < size; i++)
				ranks[i] = suffixes[i].ranks[j];
			indexes = radixSort(ranks);
			Suffix[] newSuffixes = new Suffix[size];
			for(int i = 0; i < size; i++)
				newSuffixes[i] = suffixes[indexes[i]];
			suffixes = newSuffixes;
		}	
		return suffixes;
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