# Random Word Generator!

## Usage

```bash
$ javac WordGen.java Table.java FrequencyList.java 
$ java WordGen 5 < tomsawyer.txt
```
The higher the number you input, the more human-like the text will be, and you can input any text file you'd like!

## WordGen

This algorithm takes in an integer that determines how text-like the ramble will be, that it will use to generate a random ramble based on an input text. The program is currently set to generate 10000 characters.

If using a number less than 90 (although that number should probably be less than 10), this algorithm will take the x-previous characters, and generate the next character based on those characters.

If it comes across a sequence that doesn't appear in the text, it will generate a random character from the text using a kind of 0-level analysis of the text.

If you input a number larger than or equal to 90, this algorithm will use word-level analysis.

It will start by outputting the first word of the text, and then continuously use the previous word to generate the next word, based on probability distributions. If the program comes across a word it hasn't seen before (or reaches the end of the text), it will output the first word of the text again.


