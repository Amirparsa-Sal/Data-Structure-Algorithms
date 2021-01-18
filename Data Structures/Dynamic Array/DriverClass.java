public class DriverClass{

	public static void main(String[] args){
		DynamicArray<Integer> arr = new DynamicArray<>();
		System.out.println(arr);
		arr.add(1);
		System.out.println(arr);
		arr.add(2);
		System.out.println(arr);
		arr.add(3);
		System.out.println(arr);
		arr.add(4);
		System.out.println(arr);
		arr.add(5);
		System.out.println(arr);
		arr.add(2,5);
		System.out.println(arr);
	}
}