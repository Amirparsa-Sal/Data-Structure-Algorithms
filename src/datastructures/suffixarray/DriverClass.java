package datastructures.suffixarray;

import java.util.concurrent.ThreadLocalRandom;
import datastructures.suffixarray.SuffixHandlerFast;

public class DriverClass{
	public static void main(String[] args) {
		SuffixHandlerFast shf = new SuffixHandlerFast("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		shf.construct();
		// int[] arr = new int[10000000];
		// for(int i = 0; i < 10000000; i++)
		// 	arr[i] = ThreadLocalRandom.current().nextInt(0, 10);
		// int[] arr2 = arr.clone();
		// long time1=java.lang.System.currentTimeMillis();
		// int[] sorted = shf.radixSort(arr);
		// long time2=java.lang.System.currentTimeMillis();
		// System.out.println(time2 - time1);
		// time1=java.lang.System.currentTimeMillis();
		// java.util.Arrays.sort(arr2);
		// time2=java.lang.System.currentTimeMillis();
		// System.out.println(time2 - time1);

		// time1=java.lang.System.currentTimeMillis();
		// int[] array = new int[10000000];
		// for(int i = 0; i < 10000000; i++)
		// 	array[i] = i;
		// time2=java.lang.System.currentTimeMillis();
		// System.out.println(time2 - time1);
		int[] suf = shf.getSuffixArray();
		for(Integer i : suf)
			System.out.println(i);
	}
}
