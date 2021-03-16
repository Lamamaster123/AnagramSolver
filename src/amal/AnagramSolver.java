package amal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnagramSolver {
	HashMap<String, LetterInventory> dictionary;

	/**
	 * Constructs and AnagramSolver object which will
	 * be able compute anagrams using the given list
	 * as its dictionary of valid words.
	 * 
	 * @param list The dictionary of valid words.
	 */
	public AnagramSolver(List<String> list) {
		//initialize map object
		dictionary = new HashMap<String, LetterInventory>();
		
		//store all in dictionary into map with their associated letter inventory (easy access)
		for (int i = 0; i < list.size(); i++) {
			dictionary.put(list.get(i), new LetterInventory(list.get(i)));
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
		
		//makes letter inventory for string to be analyzed
		LetterInventory stringLetters = new LetterInventory(s);
		
		ArrayList<String> matchingWords = new ArrayList<String>();
		
		//recursive helper method
		printHelper(stringLetters, max, matchingWords);

		
		
	}
	
	public void printHelper(LetterInventory stringLetters, int max, ArrayList<String> matchingWords) {
		if (stringLetters.size() == 0) {
			System.out.print(matchingWords);
		} else if (max <= 0) {
			return;
		} 
		for (Map.Entry<String, LetterInventory> entry : dictionary.entrySet()) {
			if (stringLetters.subtract(entry.getValue()) != null) {
				matchingWords.add(entry.getKey());
				printHelper(stringLetters.subtract(entry.getValue()), max - 1, matchingWords);
				matchingWords.remove(entry.getKey());
				//repeat our iteration
			}
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
	}
}
