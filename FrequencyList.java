import structure5.*;
import java.util.Random;
import java.util.Scanner;

public class FrequencyList {
    
    protected Vector<Association<String, Integer>> tableTwo; // Store the frequencies of the letters
    protected int prob; // Store the number of times the key appears in the text
    protected Random r; // Random number generator 
    
    public FrequencyList(String phrase, String text, int level) {

	r = new Random(); // Random number generator
	
	prob = 0; // Start the count at 0

	tableTwo = new Vector<Association<String, Integer>>();

	String theChar; // The character we will be adding to the Vector
	int start = 0; // Changing the place where we start looking for the key every time we find a key
	int startTwo; // Changing the place where we start looking for the key when there are multiple keys
	int charIndex; // Find the index of the Association with the key we're looking for (if it already exists)
	String newKey; // The string we will be looking for in case there are multiple occurences

	while (text.indexOf(phrase, start) >= 0) {  // Look for the phrase in the text

	    theChar = text.substring(text.indexOf(phrase, start) + level, text.indexOf(phrase, start) + level + 1); // Get the character preceding the phrase

	    charIndex = tableTwo.indexOf(new Association(theChar)); // Look to see if the character already has an Association, if it does, ignore it
					 
	    if (charIndex < 0) { // If the character doesn't already have an association, do this:
		
		tableTwo.add(new Association(theChar, prob)); // Add the character to the vector for the first time

		startTwo = 0; // Start looking at position 0

		newKey = phrase + theChar; // Create the newKey so we can search for this new string for multiple occurrences 

		// Look through the text for multiple occurrences
		while (text.indexOf(newKey, startTwo) >= 0) { 

		    prob++; // Increase the count for prob every time you find an occurrence of newKey in the text
		    startTwo = text.indexOf(newKey, startTwo) + 1; // Move the starting position

		}
		
		tableTwo.get(tableTwo.indexOf(new Association(theChar))).setValue(prob); // Set the probabilities for the FrequencyList
		
	    }

	    start = text.indexOf(phrase, start) + 1; // Move the starting position, and look again

	}
	
    }

    // Get a random next character
    public String randNext(){
	
	int num; // Random number
	num = r.nextInt(prob+1); // Pick a random number less than or equal to the number of times each "newKey" appeared in the phrase
	String theChar = "n"; // Java is worried I won't initialize theChar

	// Look through each entry in the FrequencyList and see if the prob associated with the entry is larger than or equal to the random number. If so, use that character
	for (int i = 0; i < tableTwo.size(); i++) {
	    if (tableTwo.get(i).getValue() >= num) {
		theChar = tableTwo.get(i).getKey();
		return theChar;
	    } 
	    
	}
	return theChar;
    }

}
