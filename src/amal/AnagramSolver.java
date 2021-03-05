package amal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramSolver {
	HashMap<String, LetterInventory> map;

	/**
	 * Constructs and AnagramSolver object which will
	 * be able compute anagrams using the given list
	 * as its dictionary of valid words.
	 * 
	 * @param list The dictionary of valid words.
	 */
	public AnagramSolver(List<String> list) {
		//initialize map object
		map = new HashMap<String, LetterInventory>();
		
		//store all in dictionary into map with their associated letter inventory (easy access)
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i), new LetterInventory(list.get(i)));
		}		
	}
	
	/**
	 * This method that will use recursive backtracking to find
	 * combinations of words that have the same letters as the given 
	 * string. It will print to System.out all combinations of words 
	 * from the dictionary that are anagrams of s and that include 
	 * at most max words (or unlimited number of words if max is 0). 
	 * 
	 * @param s The string to create anagrams from.
	 * @param max The max number of words in the anagram. Unlimited if 0.
	 * 		Will throw IllegalArgumentException if max is less than 0.
	 */
	public void print(String s, int max) {
		//max cannot be less than 0
		if (max < 0) {
			throw new IllegalArgumentException();
		//if max is 0, user chose infinite max value
		} else if (max == 0) {
			max = Integer.MAX_VALUE;
		}

		
		
	}
}
