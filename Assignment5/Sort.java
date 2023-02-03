//Ashna Razdan
package Assignment5;

import java.util.HashSet;
import java.util.Set;

public class Sort {
	public static void main(String[] args) {
		Integer[] array = { 44, 75, 23, 12, 64, 77, 33 };
		Sort.sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		/// 44, 75, 23, 43, 55, 12, 64, 77, 33
		
	}
	/**
	 * a helper function that swaps two elements
	 * 
	 * @param array, the unsorted array
	 * @param i, the element number to be swapped
	 * @param j, the element number to be swapped
	 */
	public static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	/**
	 * creates a new empty set to store the intervals
	 */
	public static Set<Interval> set = new HashSet<Interval>();
	/**
	 * a helper function that creates the partition for the array
	 * 
	 * @param array, the unsorted array
	 * @param lower, the lower bound of the interval
	 * @param upper, the upper bound of the interval
	 */
	public static <T extends Comparable<T>> void partition(T[] array, int lower, int upper) {
		int pivot = pivot1(array);

		int up = lower;
		int down = upper;

		do {
			while ((up < down) && (array[pivot].compareTo(array[up]) >= 0)) {
				up++;
			}
			while (array[pivot].compareTo(array[down]) < 0) {
				down--;
			}
			if (up < down) { // if up is to the left of down.
				swap(array, up, down);
			}
		} while (up < down); // Repeat while up is left of down.
		swap(array, lower, down);
	
		Interval interval1 = new Interval(lower, down-1);
		Interval interval2 = new Interval(down+1, upper);
		
		set.add(interval1);
		set.add(interval2);

	}
	/**
	 * a helper function that picks the new pivot for the partition
	 * uses median of three to pick the element
	 * @param array, the unsorted array
	 */
	public static <T extends Comparable<T>> int pivot1(T[] array) {
		int len = set.iterator().next().getUpper();
		int first = set.iterator().next().getLower();
		int mid = (len + first) / 2;
		
		if (array[first].compareTo(array[mid]) > 0) { // swap mid and first
			swap(array, first, mid);
		}
		if (array[mid].compareTo(array[len]) > 0) { // swap mid and last
			swap(array, len, mid);
		}
		if (array[first].compareTo(array[mid]) > 0) { // swap first and mid
			swap(array, first, mid);
		}
		swap(array, first, mid);

		int index = first;
				
		return index;
	}
	/**
	 *The iterative main quick sort method
	 * Creates original interval, and goes through first iteration of sorting
	 * And then sorts based on condition for each interval remaining intervals
	 * @param array, the unsorted array
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		//create first interval and add it to set
		int len = array.length - 1;
		int mid = (len - 0) / 2;
		Interval org = new Interval(0, len);
		set.add(org);
		
		// picking a pivot median of three
		if (array[0].compareTo(array[mid]) > 0) { // swap mid and first
			swap(array, 0, mid);
		}
		if (array[mid].compareTo(array[len]) > 0) { // swap mid and last
			swap(array, len, mid);
		}
		if (array[0].compareTo(array[mid]) > 0) { // swap first and mid
			swap(array, 0, mid);
		}
		swap(array, 0, mid);

		int i = 0;
		int j = len;
		T pivot = array[0];
		
		//first partition
		do {
			while ((i < len) && (pivot.compareTo(array[i]) >= 0)) {
				i++;
			}
			while (pivot.compareTo(array[j]) < 0) {
				j--;
			}
			if (i < j) { // if up is to the left of down.
				swap(array, i, j);
			}
		} while (i < j); // Repeat while up is left of down.
		swap(array, 0, j);
		//remove original interval
		set.remove(org);
		//add new intervals
		Interval interval2 = new Interval(0, j-1);
		Interval interval3 = new Interval(j+1, len);
		set.add(interval2);
		set.add(interval3);
		
		while (!set.isEmpty()) {
			
			Interval interval = set.iterator().next();
	
			if (interval.getUpper() - interval.getLower() == 1) {
				// two elements in interval
				//if a> b swap
				if (array[interval.getLower()].compareTo(array[interval.getUpper()]) > 0) {
					swap(array, interval.getUpper(), interval.getLower());}
			}
			else if (interval.getUpper() - interval.getLower() == 2) {
				//three elements in interval (best of three)
				//a>c
				if (array[interval.getLower()].compareTo(array[interval.getUpper()]) > 0) {
					swap(array, interval.getLower(), interval.getUpper());
				}
				//a>b
				if (array[interval.getLower()].compareTo(array[interval.getLower()+1]) > 0) {
					swap(array,interval.getLower(), interval.getLower()+1);
				}
				//b>c
				if (array[interval.getLower()+1].compareTo(array[interval.getUpper()]) > 0) {
					swap(array, interval.getLower()+1, interval.getUpper());
				}
			}
			else if (interval.getUpper() - interval.getLower() >= 3) {
				//greater than three elements, partition again 
				partition(array, interval.getLower(), interval.getUpper());

			}
			set.remove(interval);
		}

	}
	/**
	 * defines and creates Interval which will be added to set
	 */
	private static class Interval {
		private int lower;
		private int upper;
		
		//constructor for interval
		public Interval(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		//gets lower value of interval
		public int getLower() {
			return lower;

		}
		
		//gets upper value of interval
		public int getUpper() {
			return upper;

		}

		public boolean equals(Object o) {
			if (this.hashCode() == o.hashCode())
				return true;
			return false;

		}

		public int hashCode() {
			return lower * (lower + upper);

		}
	}
}
