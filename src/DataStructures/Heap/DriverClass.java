package DataStructures.Heap;

import java.util.Random;

public class DriverClass{

	public static void main(String[] args) {
		MinHeapDynamic<Integer> mhd = new MinHeapDynamic<>();
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