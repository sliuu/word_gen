import structure5.*;
import java.util.Random;
import java.util.Scanner;

public class Table {
    
    protected Vector<Association<String, FrequencyList>> tableOne;
    protected String theText;
    
    public Table(String text, int level) {

	theText = text; // Store the full text that will be used to create the random words

	tableOne = new Vector<Association<String, FrequencyList>>();
	
	String key; // The key will be each "level"-letter string in the text, that we will create a FrequencyList for
	int index = 0; // The index we will be changing every time we run the loop
	int position = 0; // The position of the next word
	
	// If using word-level analysis, do this:
	if (level >= 90) {
	    
	    // Look for every word in the text and add it to the vector
	    while ( theText.indexOf(" ", index) > 0) {
		position = theText.indexOf(" ", index); // Look for the space after the word we're going to add
		key = theText.substring(index, position); // Store the word
		
		if (tableOne.indexOf(new Association(key)) == -1){ // Only add it to the vector if it doesn't already exist
		    tableOne.add(new Association(key, new FrequencyList(key, text + " ", level)));
		}
		index = theText.indexOf(" ", index) + 1; // Move the index over one
	    }
	}
	else { // If not using word-level anlysis, do this:
	    
	    // Look at every 3-letter combination in the text and add it to the vector
	    for (int i = 0; i < text.length()-level; i++){
		key = text.substring(i, i+level);
		
		if (tableOne.indexOf(new Association(key)) == -1){ // Only add it to the vector if it doesn't already exist
		    tableOne.add(new Association(key, new FrequencyList(key, text, level)));
		} 
	    }
	}
    }

    // Generate a next random character using the probabilites 
    public String getNext(String theKey) {

	String randLetters = theText; // Store the text we're using to create the random words
	Random r = new Random(); // Random number generator
	String theChar; // Store the next character that will be returned
	
	int index = tableOne.indexOf(new Association(theKey)); // Look for the Association with theKey
	
	if (index >= 0) { // If there as an Association in the vector that refers to this key, then get a next random character
	    FrequencyList freqList = tableOne.get(index).getValue(); // Get the FrequencyList
	    theChar = freqList.randNext();
	} else { // If that "level"-letter string doesn't appear in the text, just generate a random character from the text
	    theChar =  "" + theText.charAt(r.nextInt(theText.length()));
	}
	
	return theChar;
	
    }

    // Generate the next word using the probabilities
    public String getNextWord(String theKey) {

	String randLetters = theText; // Store the text we're using to create the random words
	Random r = new Random(); // Random number generator
	String theWord; // Store the next word that will be returned
	int position; // For storing the random number that is generated
	
	int index = tableOne.indexOf(new Association(theKey)); // Look for the Association with theKey
	
	if (index >= 0) { // If there as an Association in the vector that refers to this key, then get a next random word
	    FrequencyList freqList = tableOne.get(index).getValue(); // Get the FrequencyList
	    theWord = freqList.randNextWord();
	} else { // If that "level"-letter string doesn't appear in the text, just generate the first word
	    theWord =  theText.substring(0, theText.indexOf(" "));
	    return theWord;
	}
	
	return theWord;

    }
       
}
