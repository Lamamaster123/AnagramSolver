package amal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnagramSolver {
	HashMap<String, LetterInventory> backupDictionary;
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
		
		//prepares backup dictionary to reset dictionary after pruning
		backupDictionary = (HashMap<String, LetterInventory>) dictionary.clone();
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
		
	    //as recursion progresses, this ArrayList will build up words in potential anagrams
		ArrayList<String> matchingWords = new ArrayList<String>();
		
		//prunes dictionary to speed up traversing in the recursive helper method
		pruneDictionary(stringLetters);
		
		//recursive helper method
		printHelper(stringLetters, max, matchingWords);

		//resets the dictionary
		dictionary = (HashMap<String, LetterInventory>) backupDictionary.clone();
		
	}
	
	/**
	 * This method will prune the dictionary to only include words that can possibly be created
	 * from the given string. This ensures that we do not traverse through these words unnecessarily
	 * and multiple times during our recursion
	 * 
	 * @param stringLetters The LetterInventory of the string that we are building anagrams from
	 */
	public void pruneDictionary(LetterInventory stringLetters) {
		// Create a Iterator to KeySet of HashMap
		Iterator<String> it = dictionary.keySet().iterator();
		// Iterate over all the dictionary words
		while (it.hasNext()) {
		    String key = it.next();
		    
		    //if there isn't enough letters to make this word, get rid of it from our dictionary
			if (stringLetters.subtract(dictionary.get(key)) == null) {
				it.remove();
			}
		}
	}
	
	
	/**
	 * This helper method will recursively build combinations of words that form anagrams using the given string
	 * 
	 * @param stringLetters The LetterInventory of the string we are building anagrams from
	 * @param max The maximum number of words that can be used, given by the user
	 * @param matchingWords Our collection bucket for building a combination of words that form an anagram
	 */
	public void printHelper(LetterInventory stringLetters, int max, ArrayList<String> matchingWords) {
		//first check if we are done and can print
		if (stringLetters.size() == 0) {
			System.out.print(matchingWords);
		//if not, check whether we have used too many words and need to stop
		} else if (max <= 0) {
			return;
		} 
		//traverse through all possible words in the dictionary, and if they can be made with the current 
		//amount of letters, repeat the entire method with the remaining letters and add the word to matchingWords
		for (Map.Entry<String, LetterInventory> entry : dictionary.entrySet()) {
			if (stringLetters.subtract(entry.getValue()) != null) {
				matchingWords.add(entry.getKey());
				printHelper(stringLetters.subtract(entry.getValue()), max - 1, matchingWords);
				
				//delete the word in order to refresh matchingWords for the next iteration
				matchingWords.remove(entry.getKey());
				//repeat our iteration
			}
		}
		
	}
}
