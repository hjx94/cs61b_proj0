public class HelloNumbers {
	public static void main (String[] args){
		int x = 0;
		int sum = x;
		while (x < 10){
			// System.out.print(x);
			sum += x;
			System.out.println(sum);
			x = x + 1;
			// System.out.print(5 + "10");
		}
	}
}