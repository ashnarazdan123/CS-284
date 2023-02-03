//Ashna Razdan
//CS 284 

package Assignment2;

public class Complexity {
	
	//Question 0
	//O(n)
	public static void method0(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	//Question 1
	//O(n^2)
	public static void method1(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {  //runs n * n times
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	//Question 2
	//O(n^3)
	public static void method2(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {   //runs n * n * n times
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}
	//Question 3
	//O(log(n))
	public static void method3(int n) {
		int counter = 0;
		for (int i = 1; i < n; i = i * 2) //runs log(n) times bc assume n is 16 
			System.out.println("Operation " + counter);  // i = { 2, 4, 8, 16} 
			counter++;
	}
	
	
	//Question 4
	//part a
	/*iteration   start   end 
	 * 1           0       31
	 * 2          16       31
	 * 3          24       31
	 * 4          28       31
	 * 5          30       31
	 * 6          31       31
	 * */
	
	//part b
	/*iteration   start   end 
	 * 1           0      63
	 * 2          32      63	 
	 * 3          48      63
	 * 4          56      63
	 * 5          60      63
	 * 6          62      63
	 * 7          63      63
	 * */
	
	//Question 5
	//There is a logarithmic relationship between n of a and the number of iterations.
	//For when length of the array is 32, the number of iterations is 5. And when the 
	//(ignoring the first baseline iteration) array is doubled to 64 there are 6 iterations. 
	//So that means that the number of iterations is log2(n). B/c log2(2^5)= 32 and log2(2^6)=64. 
	
	//Question 6
	//The time complexity of bSearch is log2(n) aka O(log(n)). This is because the array is already sorted
	//so while searching through the array the worst case scenario that the number is not in 
	//the array and can only be found after running all of the iterations. This increases the iterations at 
	//log2(n) because for every iteration the array is being halved. 
	
	//Question 7
	//O(n log(n))
	public static void method4(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) { // runs n times
			for (int j = 1; j < n; j = j * 2) { // runs log times
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
	//Question 8
	//O(log log (n))
	public static void method5(int n) {
		int counter = 0;
		for (int i = 2; i <= n; i *= i) { //ex. log2(log2(256)= 4
			System.out.println("Operation " + counter); //for 256 this runs 4 times so it works
			counter++;
		}
	}
	
	//Bonus 
	//O(2^n)2
	public static int method6(int n)
	{

	    if (n == 0) {
	    	return n;
	    }
	    if (n ==1 ) {
	    	return n;
	    }
	    else {
	    	return (method6(n - 2) + method6(n - 1));
	    }
	}
	

}
