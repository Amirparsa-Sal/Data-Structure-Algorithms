package datastructures.sparsetable;

import datastructures.sparsetable.SparseTable;

public class SumSparseTable extends SparseTable{

	public SumSparseTable(long[] arr){
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
				values[i][j] = a + b;
			}
		}
	}

	@Override
	public long rangeQuery(int startInd, int endInd){
		int len = endInd - startInd + 1;
		int row = logs[len - 1];
		int col = startInd;
		long sum = 0;
		while(len > 0){
			sum += values[row--][col];
			col += pow2(logs[len - 1]);
			len = len  - pow2(logs[len - 1]);
		}
		return sum;
	}

}

// [1,6] = [2,1] + [1,5] 