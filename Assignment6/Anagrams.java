//Ashna Razdan
package Assignment6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Anagrams {
	final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
			89, 97, 101 };;
	// hash table for alphabet
	Map<Character, Integer> letterTable = new Hashtable<Character, Integer>();
	// hash table to hold anagrams
	static Map<Long, ArrayList<String>> anagramTable;

	/**
	 * Constructor for anagrams calls build table method
	 */
	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}

	/**
	 * builds the hash table for the letters key = character letters value = int
	 * prime numbers from array list
	 */
	public void buildLetterTable() {
		int i = 0;
		// runs from a to z and increments primes per letter
		for (char alpha = 'a'; alpha <= 'z'; ++alpha) {
			letterTable.put(alpha, primes[i++]);

		}
	}

	/**
	 * adds word to anagram table by hash code if hash code already exists adds word
	 * to the end of the list if word already in the table throws Illegal Argument
	 * Exception
	 * 
	 * @param s, a string word parsed from text file
	 */
	public void addWord(String s) {
		long hc = myHashCode(s);
		ArrayList<String> word = anagramTable.get(hc);
		// first word with hash code
		if (word == null) {
			// create new list and add to hash table
			word = new ArrayList<String>();
			word.add(s);
			anagramTable.put(hc, word);

		} else {
			// hash code already exists
			// duplicate word throw error
			if (word.contains(s)) {
				throw new IllegalArgumentException("addWord: duplicate value");
			} else if (!word.contains(s)) {
				// add word to list
				word.add(s);
			}
		}

	}

	/**
	 * computes the hash code of a given word using fundamental theorem of
	 * arithmetic or prime products
	 * 
	 * @param s, a string word parsed from text file
	 */
	public long myHashCode(String s) {
		if (s == null) {
			throw new IllegalArgumentException("String cannot be empty");
		}
		long hc = 1;
		for (int i = 0; i < s.length(); i++) {
			hc = hc * letterTable.get(s.charAt(i));

		}
		return hc;
	}

	/**
	 * parses text file and builds the hash table anagramTable using the addWord
	 * method
	 *
	 * @param s, a string word parsed from text file
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);

		}
		br.close();
	}

	/**
	 * gets the entry that has the the max entries per hash code
	 *
	 */
	public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		// all key value pairs
		Set<Entry<Long, ArrayList<String>>> entrySet = anagramTable.entrySet();

		// iterator for set
		Iterator<Entry<Long, ArrayList<String>>> iterator = entrySet.iterator();
		int count = 0;
		int maxcount = 0;
		// create list to hold max entries
		ArrayList<Map.Entry<Long, ArrayList<String>>> max = new ArrayList<Entry<Long, ArrayList<String>>>();
		while (iterator.hasNext()) {
			count = 0;
			// current entry
			Entry<Long, ArrayList<String>> entry = iterator.next();
			// update counter for each entry
			for (String word : entry.getValue()) {
				count++;
			}
			// max has no value or current entry has more values then previous ones
			if (max == null || maxcount < count) {
				maxcount = count;
				count = 0;
				max = new ArrayList<Entry<Long, ArrayList<String>>>();
				max.add(entry);
			} else if (maxcount == count) { // multiple entries have the same number of values
				count = 0;
				max.add(entry);
			}

		}

		return max;
	}

	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		System.out.println("Time: " + seconds);
		System.out.println("List of max anagrams: " + maxEntries);

	}
}
