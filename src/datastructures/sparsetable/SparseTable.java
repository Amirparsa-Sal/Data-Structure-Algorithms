package datastructures.sparsetable;

public abstract class SparseTable{
	//values
	protected long[][] values;
	//saved logs
	protected int[] logs;
	//input size
	protected int n;
	//log of input size
	protected int p;

	public SparseTable(long[] arr){
		n = arr.length;
		p = log2(n);
		values = new long[p + 1][n];
		logs = new int[n];

		//filling logs
		for(int i = 0; i < n; i++)
			logs[i] = log2(i + 1);
		
		//init values and indexes
		for(int i = 0; i < n; i++)
			values[0][i] = arr[i];

		fillTable();
	}

	protected int log2(int n){
		int counter = 0;
		while(n != 0){
			counter++;
			n = n >>> 1;
		}
		return --counter;
	}

	protected int pow2(int power){
		return power == 0 ? 1 : 2 << (power - 1);
	}

	protected abstract void fillTable();

	public abstract long rangeQuery(int startInd, int endInd);

}	