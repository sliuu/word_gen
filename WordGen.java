/*

Stephanie Liu 
CS136

Problems
1) add(v) addes an element to the high end of the array, while add(i, v) adds an element (v) to a particular location - the index i
2) add(i, v) will add an element to that particular location, and shift the other entries over. set(i, v) will set the value stored at i to (v), and thus will not shift any other entries. 
3) remove(v), where v is an Object, will remove the entry in the vector with that value. If there are multiple entries in the vector with that value, the one closest to the end will be removed. remove(i), where i is an int, will remove the entry with that index. In both cases, the entire entry is removed (not just the value), so the size of the vector will shrink.
4) Since the Vector class stores Object types and not primitive types, all of the booleans would have to be converted to Objects if you wanted to use the Vector class, so BitVector would have the advantage there of making things simpler. The boolean primitive type also has the advantage of taking up less memory than the Boolean Object.

Notes:
This algorithm takes in an integer, that it will use to generate a random ramble based on an input text. If using a number less than 90 (although that number should probably be less than 10), this algorithm will take the x-previous characters, and generate the next character based on those characters. If it comes across a sequence that doesn't appear in the text, it will generate a random character from the text using a kind of 0-level analysis of the text. The program is currently set to generate 10000 characters. If you input a number larger than or equal to 90, this algorithm will use word-level analysis. It will start by outputting the first word of the text, and then continuously use the previous word to generate the next word, based on probability distributions. If the program comes across a word it hasn't seen before (or reaches the end of the text), it will output the first word of the text again.
*/

import structure5.*;
import java.util.Random;
import java.util.Scanner;

class WordGen {

    protected int level; // The level of accuracy 

    public WordGen(int level){

	// Store what level we're on, make a new random generator
	this.level = level;
	Random r = new Random();

	// Get the text and put it into a string
	Scanner in = new Scanner(System.in);
	StringBuffer textBuffer = new StringBuffer();
	while (in.hasNextLine()){
	    String line = in.nextLine();
	    textBuffer.append(line);
	    textBuffer.append("\n"); 
	}
	String text = textBuffer.toString();

	// Text is now the full contents of the input, create a table
	Table thisTable = new Table(text, level);
	
	String phrase = ""; // Make the starting "level"-number of letters
	String nextPhrase = ""; // Adding the next word in word-level analysis
	
	String nextChar; // Stores the next character to be added to the text

	// Create the starting phrase using a random number generator and the probabilities of characters in the text
	if (level >= 90) { // If in "Word" mode, just start with the first word in the text
	    phrase = text.substring(0, text.indexOf(" "));
	    System.out.print(phrase);
	} else {
	    for (int i = 0; i<level; i++) {
		phrase = phrase + "" + text.charAt(r.nextInt(text.length())); // Start with the given phrase
	    }
	}
	
	// Get the next character from the Table "length" number of times
	int length = 10000;

	// Create the randomly generated text based on the previous word (200 words)
	if (level >= 90) {
	    for (int i = 0; i<200; i++) {
		nextPhrase = thisTable.getNextWord(phrase);
		System.out.print(" " + nextPhrase);
		phrase = nextPhrase;
	    }
	} else {
	// Create the randomly generated text based on the previous "level"-number of characters
	    for (int i = 0; i<length; i++) {	    
		nextChar = thisTable.getNext(phrase);
		// After getting the next character, change the starting phrase and feed that back to the Table class to get the next character
		phrase = phrase.substring(1) + nextChar;
		System.out.print(nextChar);
	    }
	}

    }
    public static void main(String[] args) {

	if (args.length == 0) { 
	    // no args, so print usage line
	    System.out.println("Usage: java WordGen 4");
	    WordGen thisWordGen = new WordGen(4); // Run the constructor
	} else {
	    // convert first argument to theLevel
	    int theLevel = Integer.parseInt(args[0]); // Trust me java, it's an integer
	    WordGen thisWordGen = new WordGen(theLevel); // Run the constructor
	}
    }

}
