package datastructures.unionfind;

import datastructures.unionfind.UnionFind;

public class DriverClass{

	public static void main(String[] args) {
		UnionFind uf = new UnionFind(10);
		uf.unify(1,3);
		uf.unify(2,5);
		uf.unify(2,3);
		uf.unify(4,8);
		uf.unify(8,3);
		System.out.println(uf);
	}
}