package datastructures.sparsetable;

public class DriverClass{
	public static void main(String[] args) {
		MinSparseTable mst = new MinSparseTable(new long[]{1,2,3,4,0,6,7,8});
		System.out.println(mst.rangeQuery(1,6));

		SumSparseTable sst = new SumSparseTable(new long[]{1,2,3,4,0,6,7,8});
		System.out.println(sst.rangeQuery(0,3));

	}
}