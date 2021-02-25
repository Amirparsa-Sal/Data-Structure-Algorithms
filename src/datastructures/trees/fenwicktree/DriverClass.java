package datastructures.trees.fenwicktree;

import datastructures.trees.fenwicktree.FenwickTree;

public class DriverClass{

	public static void main(String[] args) {
		long[] list = new long[]{1,2,3,4,5,6,7,8};
		FenwickTree ft = new FenwickTree(list);
		System.out.println(ft.rangeSum(3,5));
		System.out.println(ft.getAt(3));
		ft.changeKey(1,3);
		System.out.println(ft);
		long[] list2 = ft.getArray();
		for (long l : list2)
			System.out.print(l + " ");
	}
}