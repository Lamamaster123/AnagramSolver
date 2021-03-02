package amal;
//this is a test

import java.util.List;

public class AnagramSolver {

	/**
	 * Constructs and AnagramSolver object which will
	 * be able compute anagrams using the given list
	 * as its dictionary of valid words.
	 * 
	 * @param list The dictionary of valid words.
	 */
	public AnagramSolver(List<String> list) {
		LetterInventory n = new LetterInventory("abc");
		
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
		
	}
}
