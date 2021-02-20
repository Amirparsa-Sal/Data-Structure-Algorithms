package DataStructures.SparseTable;

public class DriverClass{
	public static void main(String[] args) {
		MinSparseTable mst = new MinSparseTable(new int[]{1,2,3,4,0,6,7,8});
		System.out.println(mst.minRange(2,7));
	}
}