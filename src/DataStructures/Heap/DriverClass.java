package datastructures.heap;

import java.util.Random;

public class DriverClass{

	public static void main(String[] args) {
		BinaryMinHeap<Integer> mhd = new BinaryMinHeap<>();
		Random r = new Random();
		for(int i = 0; i < 50; i++){
			int x = r.nextInt(50);
			System.out.println(x);
			mhd.add(x);
			System.out.println(mhd);
			System.out.println();
		}
		System.out.println(mhd.isMinHeap());
	}
}
