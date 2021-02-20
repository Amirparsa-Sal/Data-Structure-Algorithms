package DataStructures.SparseTable;

public class MinSparseTable{
	
	private int[][] values;
	private int[][] indexes;
	private int[] logs;
	private int n;
	private int p;

	public MinSparseTable(int[] arr){
		n = arr.length;
		p = log2(n);
		values = new int[p + 1][n];
		indexes = new int[p + 1][n];
		logs = new int[n];

		//filling logs
		for(int i = 0; i < n; i++)
			logs[i] = log2(i + 1);
		
		for(int i = 0; i < n; i++){
			values[0][i] = arr[i];
			indexes[0][i] = i;
		}
		// (i,j) represents minimum of range [j, j + 2 ^ i)
		for(int i = 1; i <= p; i++){
			for(int j = 0; j <= (n - pow2(i)); j++){
				int pow2 = pow2(i - 1);
				int a = values[i-1][j];
				int b = values[i-1][j + pow2];
				values[i][j] = Math.min(a,b);
				indexes[i][j] = a < b ? indexes[i-1][j] : indexes[i-1][j + pow2];
			}
		}

	}

	public int minRange(int startInd, int endInd){
		int len = endInd - startInd + 1;
		int k = logs[len - 1];
		int leftRangeValue = getAt(k, startInd);
		int rightRangeValue = getAt(k, endInd - k + 1);
		return Math.min(leftRangeValue, rightRangeValue);
	}

	private int log2(int n){
		int counter = 0;
		while(n != 0){
			counter++;
			n = n >>> 1;
		}
		return --counter;
	}

	private int pow2(int power){
		return power == 0 ? 1 : 2 << (power - 1);
	}

	private int getAt(int i, int j){
		return j <= (n - pow2(i)) ? values[i][j] : Integer.MAX_VALUE;
	}

}