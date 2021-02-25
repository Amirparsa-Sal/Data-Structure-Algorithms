package datastructures.sparsetable;

import datastructures.sparsetable.SparseTable;

public class MinSparseTable extends SparseTable{
	
	public MinSparseTable(long[] arr){
		super(arr);
	}

	@Override
	protected void fillTable(){
		// (i,j) represents minimum of range [j, j + 2 ^ i)
		for(int i = 1; i <= p; i++){
			for(int j = 0; j <= (n - pow2(i)); j++){
				int pow2 = pow2(i - 1);
				long a = values[i-1][j];
				long b = values[i-1][j + pow2];
				values[i][j] = Math.min(a,b);
			}

			for(int j = n - pow2(i) + 1; j < n; j++)
				values[i][j] = Integer.MAX_VALUE;
		}
	}

	@Override
	public long rangeQuery(int startInd, int endInd){
		int len = endInd - startInd + 1;
		int k = logs[len - 1];
		long leftRangeValue = values[k][startInd];
		long rightRangeValue = values[k][endInd - pow2(k) + 1];
		return Math.min(leftRangeValue, rightRangeValue);
	}

}