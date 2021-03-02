package amal;

// The LetterInventory class keeps track of 26 counts for the 26 letters of the
// alphabet.  It can be constructed from a string or as the result of adding or
// subtracting two other LetterInventory objects.

import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;

public class LetterInventory {
    private int[] count;
    private int size;
    public static final int MAX = 26;  // # of letters

    public LetterInventory(LetterInventory inventory) {
        this.count = Arrays.copyOf(inventory.count, MAX);
        this.size = inventory.size; 
    }

    // post: constructs a letter inventory with the counts of the letters from
    //       the given string; ignores the case of the letters
    public LetterInventory(String s) {
        count = new int[MAX];
        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));
            if (ch <= 'z' && ch >= 'a') {
                count[ch - 'a']++;
                size++;
            }
        }
    }

    // post: constructs an empty letter inventory
    public LetterInventory() {
        this("");
    }

    // post: returns a string representation of this letter inventory as a
    //       bracketed sequence of letters in alphabetical order with n
    //       occurrences of a letter that has a count of n in the inventory
    public String toString() {
        String result = "[";
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < count[i]; j++)
                result += (char)('a' + i);
        return result + "]";
    }

    // pre : letter is a letter of the alphabet (throws
    //        IllegalArgumentException if not)
    // post: returns the count for the given letter; ignores case
    public int get(char letter) {
        letter = check(letter);
        return count[letter - 'a'];
    }

    // pre : letter is a letter of the alphabet (throws
    //        IllegalArgumentException if not)
    //       value is >= 0 (throws IllegalArgumentException if not)
    // post: sets the count for the given letter to the given value; ignores
    //       case
    public void set(char letter, int value) {
        if (value < 0)
            throw new IllegalArgumentException("can't set to negative");
        letter = check(letter);
        int index = letter - 'a';
        size -= count[index];
        count[index] = value;
        size += count[index];
    }

    // post: returns a LetterInventory that is the result of subtracting the
    //       given inventory from this one (each new count is the difference
    //       between the counts of the two inventories); returns null if any
    //       resulting count would be negative
    public LetterInventory subtract(LetterInventory other) {
        if (size < other.size)  // sanity check
            return null;
        LetterInventory result = new LetterInventory();
        for (int i = 0; i < MAX; i++) {
            int newCount = count[i] - other.count[i];
            if (newCount < 0)
                return null;
            result.count[i] = newCount;
        }
        result.size = size - other.size;
        return result;
    }

    // post: returns a LetterInventory that is the result of adding the given
    //       inventory to this one (each new count is the sum of the counts of
    //       the two inventories)
    public LetterInventory add(LetterInventory other) {
        LetterInventory result = new LetterInventory();
        for (int i = 0; i < MAX; i++) {
            result.count[i] = count[i] + other.count[i];
        }
        result.size = size + other.size;
        return result;
    } 
 
    public double getLetterPercentage(char letter) {
        letter = check(letter);
        if (this.size == 0) {
            return 0;
        }
        return (double)this.get(letter) / this.size;
    }

    // post: returns the sum of all of the counts in the inventory
    public int size() {
        return size;
    }

    // post: returns true if all counts are 0; returns false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: throws an IllegalArgumentException if given character is not a
    //       letter; otherwise returns the lowercase version of the letter
    private char check(char letter) {
        letter = Character.toLowerCase(letter);
        if (letter < 'a' || letter > 'z')
            throw new IllegalArgumentException("illegal index: " + letter);
        return letter;
    }
}