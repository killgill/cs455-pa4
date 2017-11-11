// Name: 
// USC NetID: 
// CS 455 PA4
// Fall 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.Collections;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   private File inFile;
   private HashMap<String,ArrayList<String>> dict;
   


   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      inFile = new File(fileName);
      try (Scanner in = new Scanner(inFile)) {
	 dict = createDict(in);
      }
      catch (FileNotFoundException exception) {
	 System.out.println("File not found: " + exception.getMessage());
	 System.exit(0);
      }
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
       String canon = getCanon(s);
       if (dict.containsKey(canon)){
	  return dict.get(canon);
       }
       return new ArrayList<String>(); 
   }

   private HashMap<String,ArrayList<String>> createDict(Scanner in){
      HashMap<String,ArrayList<String>> temp = new HashMap<String,ArrayList<String>>();
      String word;
      String canon;
      while(in.hasNext()) {
	 word = in.next();
	 canon = getCanon(word);
	 ArrayList<String> temp2 = new ArrayList<String>();
	 if (!temp.containsKey(canon)){
	    temp2.add(word);
	    temp.put(canon,temp2);
	 }
	 else {
	    temp2 = temp.get(canon);
	    temp2.add(word);
	    Collections.sort(temp2);
	    temp.put(canon,temp2);
	 }
      }
      return temp;
   }

   /**
   adapted from http://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/ 
   */
   public String getCanon(String s) {
      char charArray[] = s.toCharArray();
      Arrays.sort(charArray);
      return new String(charArray);
   }
   
}
