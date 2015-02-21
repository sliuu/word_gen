import structure5.*;
import java.util.Random;
import java.util.Scanner;

class WordGen {

    public static void main(String[] args) {

	int level = 3;
	
	// Get the text and put it into a string
	Scanner in = new Scanner(System.in);
	StringBuffer textBuffer = new StringBuffer();
	while (in.hasNextLine()){
	    String line = in.nextLine();
	    textBuffer.append(line);
	    // textBuffer.append("\n");
	}
	String text = textBuffer.toString();

	// Text is now the full contents of the input, create a table
	Table thisTable = new Table(text, level);
	String phrase = "theeeeee";
	phrase = phrase.substring(0, level);
	
	String nextChar; // Stores the next character to be added to the text
	System.out.print(phrase); // Start with the given phrase

	// Get the next character from the Table "length" number of times
	int length = 5000;
	for (int i = 0; i<length; i++) { 
	    nextChar = thisTable.getNext(phrase);
	    // After getting the next character, change the starting phrase and feed that back to the Table class to get the next character
	    phrase = phrase.substring(1) + nextChar;
	    System.out.print(nextChar);
	}

    }

}
