// Ashna Razdan CS-284
package Assignment1;

import java.util.Random;


public class CoinPurse {
	private int numGalleons;
	private int numSickles;
	private int numKnuts;
	private final static int CAPACITY = 256; //max capacity of coin purse
	
	public CoinPurse() {  //initialize coin purse 
		numGalleons = 0;
		numSickles = 0;
		numKnuts = 0;
	}

	public CoinPurse(int g, int s, int k) {  //constructor
		this.numGalleons = g;
		this.numSickles = s;
		this.numKnuts = k;
		
		if((g+s+k) > CAPACITY || (g < 0) || (s < 0) || (k <0))
			throw new IllegalArgumentException("CoinPurse cannot exceed 256 coins.");
	}
//methods to deposit coins
//throws illegal argument if n is neg
	public void depositGalleons(int n) {
		if (n < 0 || n > CAPACITY)
			throw new IllegalArgumentException("Cannot deposit a neg number of galleons.");
		numGalleons = numKnuts + n;
	}

	public void depositSickles(int n) {
		if (n < 0 || n > CAPACITY)
			throw new IllegalArgumentException("Cannot deposit a neg number of sickles.");
		numSickles = numSickles + n;
	}

	public void depositKnuts(int n) {
		if (n < 0 || n > CAPACITY)
			throw new IllegalArgumentException("Cannot deposit a neg number of knuts.");
		numKnuts = numKnuts + n;

	}
//methods to withdraw coins
//throws illegal argument if n is neg

	public void withdrawGalleons(int n) {
		if (n < 0 || n > numGalleons)
			throw new IllegalArgumentException("Cannot withdraw a neg number of galleons");
		numGalleons = numGalleons - n;
	}

	public void withdrawSickles(int n) {
		if (n < 0 || n > numSickles)
			throw new IllegalArgumentException("Cannot withdraw a neg number of sickles");
		numSickles = numSickles - n;
	}

	public void withdrawKnuts(int n) {
		if (n < 0 || n > numKnuts)
			throw new IllegalArgumentException("Cannot withdraw a neg number of knuts");
		numKnuts = numKnuts - n;
	}


	public int numCoins() { //total num of coins
		return numGalleons + numSickles + numKnuts;
	}

	public int totalValue() { //total value of coins in knuts 
		return (numGalleons * 493) + (numSickles * 29) + numKnuts;
	}

	public String toString() { //to string method 
		return ("There are " + numGalleons + " galleons, " + numSickles + " sickles, and " + numKnuts + " knuts.");
	}

	public boolean exactChange(int n) { //checks if n can make the value with coins in purse
		int g1 = n / 493;  //division and mod to get the num of each type of coin
		n = n % 493;
		int s1 = n / 29;
		n = n % 29;
		int k1 = n;

		int coins[] = { g1, s1, k1 };  //set in an int[]

		if (coins[0] > numGalleons || coins[1] > numSickles || coins[2] > numKnuts)
			return false;
		return true;

	}

	public int[] withdraw(int n) { //removes the coins from coinpurse if exact change is true
		int g1 = n / 493;
		n = n % 493;
		int s1 = n/29;
		n = n % 29;
		int k1 = n;
		
		int coins[] = { g1, s1 , k1}; 
		
		if (exactChange(n) == true) {
			numGalleons = numGalleons - g1;
			numSickles = numSickles - s1;
			numKnuts = numKnuts - k1;
			
		}
		else {   //if coinpurse is false makes the next best match  
			if(n < 0 || n > CAPACITY) //throws exception for exceeding capacity or neg n
				throw new IllegalArgumentException("Cannot withdraw a neg number of coins or number is greater than capacity.");
			if (numSickles > 0) {
			 coins[1] = s1+1;
			 coins[2]= 0;
			}
			else if (numGalleons > 0) {
			 coins[0]= g1 +1;
			 coins[1] = 0;
			 coins[2] = 0;
			}
			numGalleons = numGalleons - g1; //withdraw coins
			numSickles = numSickles - s1;
			numKnuts = numKnuts - k1;
		
		}	
		return coins;
	}


	public int drawRandomCoin() { //draws random coin based on probability
		if (totalValue() <= 0)
			throw new IllegalArgumentException("There are no coins in coin purse.");
		Random randnum = new Random();
		int total = numGalleons + numSickles + numKnuts;
		int randomNumber = randnum.nextInt(total);
		if (randomNumber <= numGalleons)
			return 2;
		else if (randomNumber <= numSickles)
			return 1;
		else
			return 0;
	}
	
	public int[] drawRandSequence(int n) { //draws a random sequence of coins using drawrandcoin()
		if (totalValue() <= 0)
			throw new IllegalArgumentException("There are no coins in coin purse.");
		int[] randomSeq = new int[n];
	      for (int i = 0; i < randomSeq.length; i++) {
	         randomSeq[i] = drawRandomCoin();
	         
	      }
	    return randomSeq;
	}

	public static int compareSequences(int[] coinSeq1, int[] coinSeq2) {
		if (coinSeq1.length != coinSeq2.length) //compares length of arrays
			throw new IllegalArgumentException("The sequence lengths are not equal.");
		int s1count = 0;
		int s2count = 0;

		for (int i = 0; i < coinSeq1.length; i++) { //finds the difference 
			int coinDiff = coinSeq1[i] - coinSeq2[i];

			if (coinDiff <= -1) {  //determines winner by incrementing count
				s1count++;
			} else if (coinDiff >= 1) {
				s2count++;
			} 
		}
		
		if (s1count < s2count) {
			return 1;
		} else if (s1count > s2count) {
			return -1;
		} else
			return 0;
	}

}
